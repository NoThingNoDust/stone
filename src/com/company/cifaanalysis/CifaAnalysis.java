package com.company.cifaanalysis;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CifaAnalysis {

    private static Logger log = Logger.getLogger(CifaAnalysis.class.getName());

    public int analysis(String filePath) {
        //文件阅读器
        CharBuffer charBuffer;
        try {
            charBuffer = this.fileRead(filePath);
            log.log(Level.INFO, charBuffer.toString());
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //词法分析器
        return 1;
    }


    public CharBuffer fileRead(String filePath) throws Exception {
            InputStream in = new FileInputStream(filePath);
            return this.decode(this.makeByteBuffer(in));
    }


    public ByteBuffer makeByteBuffer(InputStream in)
            throws IOException {
        int limit = in.available();
        if (limit < 1024) limit = 1024;
        ByteBuffer result = ByteBuffer.allocate(20480 + 20480>>1);
        int position = 0;
        while (in.available() != 0) {
            if (position >= limit)
                // expand buffer
                result = ByteBuffer.
                        allocate(limit <<= 1).
                        put((ByteBuffer)result.flip());
            int count = in.read(result.array(),
                    position,
                    limit - position);
            if (count < 0) break;
            result.position(position += count);
        }
        return (ByteBuffer)result.flip();
    }

    public CharBuffer decode(ByteBuffer inbuf) {
        String encodingName = new OutputStreamWriter(new ByteArrayOutputStream()).getEncoding();;
        CharsetDecoder decoder;
        try {
            decoder = getDecoder(encodingName);
        } catch (Exception e) {
            log.log(Level.FINE, "unsupported.encoding", encodingName);
            return (CharBuffer)CharBuffer.allocate(1).flip();
        }

        float factor = decoder.averageCharsPerByte() * 0.8f + decoder.maxCharsPerByte() * 0.2f;
        CharBuffer dest = CharBuffer.allocate(10 + (int)(inbuf.remaining()*factor));

        while (true) {
            CoderResult result = decoder.decode(inbuf, dest, true);
            dest.flip();

            if (result.isUnderflow()) {
                if (dest.limit() == dest.capacity()) {
                    dest = CharBuffer.allocate(dest.capacity()+1).put(dest);
                    dest.flip();
                }
                return dest;
            } else if (result.isOverflow()) {
                int newCapacity =
                        10 + dest.capacity() +
                                (int)(inbuf.remaining()*decoder.maxCharsPerByte());
                dest = CharBuffer.allocate(newCapacity).put(dest);
            } else if (result.isMalformed() || result.isUnmappable()) {
                inbuf.position(inbuf.position() + result.length());
                dest.position(dest.limit());
                dest.limit(dest.capacity());
                dest.put((char)0xfffd);
            } else {
                throw new AssertionError(result);
            }
        }
    }


    public CharsetDecoder getDecoder(String encodingName) {
        Charset cs = Charset.forName(encodingName);
        CharsetDecoder decoder = cs.newDecoder();
        CodingErrorAction action = CodingErrorAction.REPORT;
        return decoder.onMalformedInput(action).onUnmappableCharacter(action);
    }

}
