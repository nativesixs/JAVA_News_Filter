package org.example;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    final String API_KEY = Main.API_KEY;
    ApiController api = new ApiController();
    @FXML private ChoiceBox<String> categoryBox;
    @FXML private ChoiceBox<String> choiceBox;
    @FXML private ChoiceBox<String> countryBox;
    @FXML private TextField keywordField;
    @FXML private TextArea resultField;
    @FXML private Button searchBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> options=FXCollections.observableArrayList("Trending","All");
        choiceBox.setValue("Trending");
        choiceBox.setItems(options);

        ObservableList<String> country=FXCollections.observableArrayList("Any","ae","ar","at","au","be","bg","br","ca","ch","cn","co","cu","cz","de","eg","fr","gb","gr","hk","hu","id","ie","il","in","it","jp","kr","lt","lv","ma","mx","my","ng","nl","no","nz","ph","pl","pt","ro","rs","ru","sa","se","sg","si","sk","th","tr","tw","ua","us","ve","za");
        countryBox.setValue("Any");
        countryBox.setItems(country);

        ObservableList<String> category=FXCollections.observableArrayList("Any","business","entertainment","general","health","science","sports","technology");
        categoryBox.setValue("Any");
        categoryBox.setItems(category);
    }
    @FXML
    public void choiceHandle() throws IOException, ClassNotFoundException {
        if(choiceBox.getValue()=="Trending"){
            callApi(1);
        } else if (choiceBox.getValue()=="All") {
            callApi(2);
        }
    }

    public String keywordHandle(){
        String s = keywordField.getText();
        if(s.length()==0){ //default value
            return "";
        }
        String[] parsed=s.split(",");
        String keywords="";
        for(int i=0;i<parsed.length;i++){
            keywords+="q="+parsed[i]+"&";
        }
        return keywords;
    }


    public String countryHandle(){
        String s = countryBox.getValue();
        if(s=="Any"){ //default value
            s="";
            return s;
        }else {
            s="country="+s+"&";
        return s;
        }
    }

    public String categoryHandle(){
        String s = categoryBox.getValue();
        if(s=="Any"){ //default value
            s="";
            return s;
        }else {
            s="category="+s+"&";
            return s;
        }
    }

    public void callApi(int choice) throws IOException, ClassNotFoundException {
        resultField.clear();
        ObservableList result;
        ApiController api = new ApiController();
        String keywords = keywordHandle();
        String country=countryHandle();
        String category=categoryHandle();
        result = api.getData(choice,API_KEY,keywords,country,category);
        for(Object o : result) {
            resultField.appendText((String) o);
        }
    }






}

