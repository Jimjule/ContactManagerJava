interface InputOutput {

     void display(String message);

    String confirmInput(String field, boolean isAnUpdate);

    int getNumberInput();

    String getInput(String detail);

    Boolean validateInput(String detail, String userInput, boolean isAnUpdate);
}
