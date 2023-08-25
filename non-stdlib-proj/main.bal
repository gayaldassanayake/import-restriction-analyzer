import ballerina/io;

public function printDescription() {
    io:println("This is a project with an organization that is not ballerina" +
    "or ballerinax. Hence the compiler plugin should display an error diagnostic");
}
