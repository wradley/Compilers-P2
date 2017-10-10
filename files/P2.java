import java.util.*;
import java.io.*;
import java_cup.runtime.*;  // defines Symbol

/**
 * This program is to be used to test the Scanner.
 * This version is set up to test all tokens, but more code is needed to test
 * other aspects of the scanner (e.g., input that causes errors, character
 * numbers, values associated with tokens)
 */
public class P2 {
    public static void main(String[] args) throws IOException {

        // exception may be thrown by yylex
        // test all tokens
        testAllTokens();
        CharNum.num = 1;

	// Redirect our test's output to p2output.txt
	switch(args[0]) {
	case "untermStringLitTest.in":
		testStrings("untermStringLitTest.in");
		break;
	case "badStringLitTest.in":
		testStrings("badStringLitTest.in");
		break;
	case "badIntLitTest.in":
		testInts("badIntLitTest.in");
		break;
	default:
		System.out.println("[" + args[0] + "] not a specified test.");
		break;
	}


        // ADD CALLS TO OTHER TEST METHODS HERE
    }

    /**
     * testInts
     *
     * Read integer input from an input text file.
     * For each int literal, copy it directly to the output file if it is valid.
     * Else, output the correct error message if the int literal is invalid.
     * If any non int tokens are found it will print an error message.
     *
     */
    private static void testInts(String in)throws IOException {
        // open input and output files
        FileReader inFile = null;
        try {
            inFile = new FileReader(in);
        } catch (FileNotFoundException ex) {
            System.err.println("File " + in + " not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.INTLITERAL:
                System.out.println(((IntLitTokenVal)token.value).intVal);
                break;
            default:
                System.out.println("ERROR: NON-INT TOKEN IN TEST");
            } // end switch

            token = scanner.next_token();
        } // end while
    }

    /**
     * testStrings
     *
     * Read an input file for String literals.
     * For each correct string, write it directly to the output. If there are any
     * bad strings, or unterminated strings, the error will be written to output.
     * The tests also accepts identifiers.
     * Anything but String ltierals and identifiers will produce an error message.
     *
     */
    private static void testStrings(String in) throws IOException {
        // open input and output files
        FileReader inFile = null;
        try {
            inFile = new FileReader(in);
        } catch (FileNotFoundException ex) {
            System.err.println("File " + in + " not found.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.STRINGLITERAL:
                System.out.println(((StrLitTokenVal)token.value).strVal); // This will be redirected to an output file
                break;
	    case sym.ID:
                System.out.println(((IdTokenVal)token.value).idVal); // Valid identifiers will also be redirected to output file
		break;
            default:
                System.out.println("ERROR: UNEXPECTED TOKEN IN TEST"); // We purposely only use String Literals and ID tokens in this test
            } // end switch

            token = scanner.next_token();
        } // end while
    }

    /**
     * testAllTokens
     *
     * Open and read from file allTokens.txt
     * For each token read, write the corresponding string to allTokens.out
     * If the input file contains all tokens, one per line, we can verify
     * correctness of the scanner by comparing the input and output files
     * (e.g., using a 'diff' command).
     */
    private static void testAllTokens() throws IOException {
        // open input and output files
        FileReader inFile = null;
        PrintWriter outFile = null;
        try {
            inFile = new FileReader("allTokens.in");
            outFile = new PrintWriter(new FileWriter("allTokens.out"));
        } catch (FileNotFoundException ex) {
            System.err.println("File allTokens.in not found.");
            System.exit(-1);
        } catch (IOException ex) {
            System.err.println("allTokens.out cannot be opened.");
            System.exit(-1);
        }

        // create and call the scanner
        Yylex scanner = new Yylex(inFile);
        Symbol token = scanner.next_token();
        while (token.sym != sym.EOF) {
            switch (token.sym) {
            case sym.BOOL:
                outFile.println("bool");
                break;
	    case sym.INT:
                outFile.println("int");
                break;
            case sym.VOID:
                outFile.println("void");
                break;
            case sym.TRUE:
                outFile.println("true");
                break;
            case sym.FALSE:
                outFile.println("false");
                break;
            case sym.STRUCT:
                outFile.println("struct");
                break;
            case sym.CIN:
                outFile.println("cin");
                break;
            case sym.COUT:
                outFile.println("cout");
                break;
            case sym.IF:
                outFile.println("if");
                break;
            case sym.ELSE:
                outFile.println("else");
                break;
            case sym.WHILE:
                outFile.println("while");
                break;
            case sym.RETURN:
                outFile.println("return");
                break;
            case sym.ID:
                outFile.println(((IdTokenVal)token.value).idVal);
                break;
            case sym.INTLITERAL:
                outFile.println(((IntLitTokenVal)token.value).intVal);
                break;
            case sym.STRINGLITERAL:
                outFile.println(((StrLitTokenVal)token.value).strVal);
                break;
            case sym.LCURLY:
                outFile.println("{");
                break;
            case sym.RCURLY:
                outFile.println("}");
                break;
            case sym.LPAREN:
                outFile.println("(");
                break;
            case sym.RPAREN:
                outFile.println(")");
                break;
            case sym.SEMICOLON:
                outFile.println(";");
                break;
            case sym.COMMA:
                outFile.println(",");
                break;
            case sym.DOT:
                outFile.println(".");
                break;
            case sym.WRITE:
                outFile.println("<<");
                break;
            case sym.READ:
                outFile.println(">>");
                break;
            case sym.PLUSPLUS:
                outFile.println("++");
                break;
            case sym.MINUSMINUS:
                outFile.println("--");
                break;
            case sym.PLUS:
                outFile.println("+");
                break;
            case sym.MINUS:
                outFile.println("-");
                break;
            case sym.TIMES:
                outFile.println("*");
                break;
            case sym.DIVIDE:
                outFile.println("/");
                break;
            case sym.NOT:
                outFile.println("!");
                break;
            case sym.AND:
                outFile.println("&&");
                break;
            case sym.OR:
                outFile.println("||");
                break;
            case sym.EQUALS:
                outFile.println("==");
                break;
            case sym.NOTEQUALS:
                outFile.println("!=");
                break;
            case sym.LESS:
                outFile.println("<");
                break;
            case sym.GREATER:
                outFile.println(">");
                break;
            case sym.LESSEQ:
                outFile.println("<=");
                break;
            case sym.GREATEREQ:
                outFile.println(">=");
                break;
	    case sym.ASSIGN:
                outFile.println("=");
                break;
			default:
				outFile.println("UNKNOWN TOKEN");
            } // end switch

            token = scanner.next_token();
        } // end while
        outFile.close();
    }
}
