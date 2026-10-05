import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Formulário simples de cadastro de pessoa, conforme o enunciado da atividade 2.
 * A mesma classe inicia a aplicação e trata os componentes definidos no FXML.
 */
public class FormularioCadastro extends Application {
    @FXML
    private TextField cpf;
    @FXML
    private TextField nome;
    @FXML
    private TextField endereco;
    @FXML
    private ComboBox<String> estado;
    @FXML
    private ComboBox<String> cargo;
    @FXML
    private Button imprimirDados;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FormularioCadastro.fxml"));
        loader.setController(this);
        GridPane formulario = loader.load();

        Scene scene = new Scene(formulario, 480, 320);
        scene.getStylesheets().add(getClass().getResource("formulario.css").toExternalForm());

        stage.setTitle("Formulário para cadastro de Pessoa");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void initialize() {
        ObservableList<String> estados = FXCollections.observableArrayList(
                "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal",
                "Espírito Santo", "Goiás", "Maranhão", "Mato Grosso", "Mato Grosso do Sul",
                "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí",
                "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia",
                "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins");
        estado.setItems(estados);

        ObservableList<String> cargos = FXCollections.observableArrayList(
                "Assistente", "Analista", "Gerente", "Gerente de Marketing");
        cargo.setItems(cargos);

        // A expressão lambda trata o clique no botão, como solicitado no PDF.
        imprimirDados.setOnAction(evento -> mostrarDados());
    }

    private void mostrarDados() {
        String estadoSelecionado = estado.getValue();
        String cargoSelecionado = cargo.getValue();
        if (estadoSelecionado == null) {
            estadoSelecionado = "Não selecionado";
        }
        if (cargoSelecionado == null) {
            cargoSelecionado = "Não selecionado";
        }

        String dados = "CPF: " + cpf.getText()
                + "\nNome: " + nome.getText()
                + "\nEndereço: " + endereco.getText()
                + "\nEstado: " + estadoSelecionado
                + "\nCargo: " + cargoSelecionado;

        Alert mensagem = new Alert(Alert.AlertType.INFORMATION);
        mensagem.initOwner(cpf.getScene().getWindow());
        mensagem.setTitle("Mensagem");
        mensagem.setHeaderText(null);
        mensagem.setContentText(dados);
        mensagem.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
