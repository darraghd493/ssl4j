import me.darragh.ssl4j.interpreter.Interpreter;
import me.darragh.ssl4j.parser.Parser;

void main() {
    Parser parser = new Parser();
    Interpreter interpreter = new Interpreter();

    String code = "jfffavvflflqvvvviifblflflfff"; // https://codegolf.stackexchange.com/questions/120052/make-me-a-square/198619#198619
    interpreter.interpret(parser.parse(code));
}