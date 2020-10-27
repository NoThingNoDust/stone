package com.company.cifaanalysis;

import com.sun.tools.javac.util.Name;

public class Token {
//    private final String key;
//    private final String value;
//
//    /** The token kind */
//    public final TokenKind kind = TokenKind.CHARLITERAL;
//
//    /** The start position of this token */
//    public final int pos = 0;
//
//    /** The end position of this token */
//    public final int endPos = 1;
//
//    Token(TokenKind kind, int pos, int endPos) {
//        this.kind = kind;
//        this.pos = pos;
//        this.endPos = endPos;
//        checkKind();
//    }

    public static final Token EOF = new Token(-1){}; // end of file
    public static final String EOL = "\\n";          // end of line
    private int lineNumber;

    Token(int line) {
        lineNumber = line;
    }

    Token(String key, String value) {
//        this.key = key;
//        this.value = value;

    }

//
//
//    protected void checkKind() {
//        if (kind.tag != Tag.DEFAULT) {
//            throw new AssertionError("Bad token kind - expected " + Tag.STRING);
//        }
//    }

    public Name name() {
        throw new UnsupportedOperationException();
    }

    public String stringVal() {
        throw new UnsupportedOperationException();
    }

    public int radix() {
        throw new UnsupportedOperationException();
    }

//    public String getValue() {
//        return value;
//    }
//
//    public String getKey() {
//        return key;
//    }

    public String getText() {
        return "xxxxxx";
    }

    public int getLineNumber() {
        return 1;
    }


//    /**
//     * Preserve classic semantics - if multiple javadocs are found on the token
//     * the last one is returned
//     */
//    public Tokens.Comment comment(Tokens.Comment.CommentStyle style) {
//        List<Tokens.Comment> comments = getComments(Tokens.Comment.CommentStyle.JAVADOC);
//        return comments.isEmpty() ?
//                null :
//                comments.head;
//    }

    /**
     * Preserve classic semantics - deprecated should be set if at least one
     * javadoc comment attached to this token contains the '@deprecated' string
     */
//    public boolean deprecatedFlag() {
//        for (Tokens.Comment c : getComments(Tokens.Comment.CommentStyle.JAVADOC)) {
//            if (c.isDeprecated()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private List<Tokens.Comment> getComments(Tokens.Comment.CommentStyle style) {
//        if (comments == null) {
//            return List.nil();
//        } else {
//            ListBuffer<Tokens.Comment> buf = new ListBuffer<>();
//            for (Tokens.Comment c : comments) {
//                if (c.getStyle() == style) {
//                    buf.add(c);
//                }
//            }
//            return buf.toList();
//        }
//    }
}
