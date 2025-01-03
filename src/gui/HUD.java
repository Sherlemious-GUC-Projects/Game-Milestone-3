package gui;

import engine.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import javafx.stage.Stage;

public class HUD {
	public static Node hudHero = hudHero();
    public static Hero currentHero = GameView.current_hero;
    public static Node hudHero(){
        currentHero = GameView.current_hero;
        String s = "";
        int vCount;
        int sCount;
        
        if(currentHero instanceof Fighter)s="Fighter";
        if(currentHero instanceof Medic)s="Medic";
        if(currentHero instanceof Explorer)s="Explorer";
        VBox characterInfo = new VBox();
        characterInfo.setMinWidth(150);
        characterInfo.setStyle("-fx-background-color: #569d6f;");
        characterInfo.setAlignment(Pos.TOP_CENTER);
        characterInfo.setSpacing(10);
        Label characterName = new Label("Name: " + currentHero.getName());
        Label characterHealth = new Label("HP: " + currentHero.getCurrentHp() + "/" + currentHero.getMaxHp());
        Label characterAttackDamage = new Label("Attack Damage: " + currentHero.getAttackDmg());
        Label characterNumberOfMoves = new Label("Actions Available: " + currentHero.getActionsAvailable());
        Label characterType = new Label("Type: " + s);
        Label VaccineC = new Label("Vaccines: " + currentHero.getVaccineInventory().size());
        Label SupplyC = new Label("Supplies: " + currentHero.getSupplyInventory().size());
		Label placeHolder = new Label(" ");

        characterName.setFont(Font.font("Comic Sans", FontWeight.BOLD, 10));
        characterHealth.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterAttackDamage.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterNumberOfMoves.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterType.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        VaccineC.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        SupplyC.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

		// Font colours for the labels
		characterName.setStyle("-fx-text-fill: #dbdbdb;");
		characterHealth.setStyle("-fx-text-fill: #dbdbdb;");
		characterAttackDamage.setStyle("-fx-text-fill: #dbdbdb;");
		characterNumberOfMoves.setStyle("-fx-text-fill: #dbdbdb;");
		characterType.setStyle("-fx-text-fill: #dbdbdb;");
		VaccineC.setStyle("-fx-text-fill: #dbdbdb;");
		SupplyC.setStyle("-fx-text-fill: #dbdbdb;");

        characterInfo.getChildren().addAll(placeHolder, characterName, characterHealth, characterAttackDamage,
                characterNumberOfMoves, characterType,SupplyC,VaccineC);

        characterInfo.autosize();
        return characterInfo;
    }
    public static Node hudAttack(Stage primaryStage){
    	    VBox v = new VBox();
    	    Button b = new Button("Attack!!!");
    	    b.setMinSize(100, 100);
    	    v.setSpacing(10);
	    	Label l = new Label("\nPlease Select a Zombie");
	    	b.setOnAction(e -> {
	            Buttons.AttackButton(GameView.current_hero, GameView.current_zombie, primaryStage);
//				Label x = new Label("Zombie Health: " + GameView.current_zombie.getCurrentHp() + "/" + GameView.current_zombie.getMaxHp());
//				x.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
//				v.getChildren().add(x);
	        });
	    	v.getChildren().addAll(l,b);
	    	return v;
    }
	public static  Node hudCure(Stage primaryStage) {
		 VBox v = new VBox();
 	    Button b = new Button("Cure!!!");
 	    b.setMinSize(100, 100);
 	    v.setSpacing(10);
	    	Label l = new Label("please select a zombie");
	    	b.setOnAction(e -> {
	            Buttons.cureButton(GameView.current_hero, GameView.current_zombie, primaryStage);
	        });
	    	v.getChildren().addAll(l,b);
	    	return v;
	}
	public static  Node hudSpecial(Hero h, Stage primaryStage) {
		 VBox v = new VBox();
	    Button b = new Button("Specialll!!!");
	    b.setMinSize(100, 100);
	    v.setSpacing(10);
	    	Label l = new Label("please select a Hero");
	    	b.setOnAction(e -> {
	            Buttons.specialButtonM(h, GameView.current_hero, primaryStage);
	        });
	    	v.getChildren().addAll(l,b);
	    	return v;
	}
}
