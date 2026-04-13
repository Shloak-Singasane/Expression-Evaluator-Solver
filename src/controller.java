import java.awt.*;

class Controller {
    private UIComponents ui;
    private TreeBuilder builder;
    private Evaluator evaluator;

    public Controller(UIComponents ui) {
        this.ui = ui;
        this.builder = new TreeBuilder();
        this.evaluator = new Evaluator();

        ui.calcBtn.addActionListener(e -> runEvaluation());
    }

    private void runEvaluation() {
        String input = ui.inputField.getText().trim();
        ui.logArea.setText("");

        try {
            String[] tokens = input.split("\\s+");
            StringBuilder log = new StringBuilder();

            log.append("1. Tokenizing input...\n");

            Node root = builder.buildTree(tokens, log);
            log.append("2. Expression Tree built successfully.\n");

            log.append("3. Evaluating expression...\n");
            String result = evaluator.evaluate(root);

            ui.logArea.setText(log.toString());

            ui.resultLabel.setText("Result: " + result);
            ui.resultLabel.setForeground(new Color(0, 100, 0));

        } catch (Exception ex) {
            ui.logArea.append("\nERROR: " + ex.getMessage());
            ui.resultLabel.setText("Result: ERROR");
            ui.resultLabel.setForeground(Color.RED);
        }
    }
}
