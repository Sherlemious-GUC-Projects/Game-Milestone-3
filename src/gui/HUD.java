package gui;

import engine.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import model.characters.Hero;
import model.characters.Zombie;

public class HUD {
    public static Hero currentHero = GameView.current_hero;
    public static Node hudHero(){
        currentHero = GameView.current_hero;
        VBox characterInfo = new VBox();
        characterInfo.setMinWidth(150);
        characterInfo.setStyle("-fx-background-color: #6b6b6b;");
        characterInfo.setAlignment(Pos.TOP_CENTER);
        characterInfo.setSpacing(10);
        Label characterName = new Label("Name: " + currentHero.getName());
        Label characterHealth = new Label("HP: " + currentHero.getCurrentHp() + "/" + currentHero.getMaxHp());
        Label characterAttackDamage = new Label("Attack Damage: " + currentHero.getAttackDmg());
        Label characterNumberOfMoves = new Label("Actions Available: " + currentHero.getActionsAvailable());
        Label characterType = new Label("Type: ");
        characterName.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterHealth.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterAttackDamage.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterNumberOfMoves.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterType.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        characterInfo.getChildren().addAll(characterName, characterHealth, characterAttackDamage,
                characterNumberOfMoves, characterType);

        characterInfo.autosize();
        return characterInfo;
    }
    public static Node hudAttack(){
    	    VBox v = new VBox();
    	    Button b = new Button("Atack!!!");
    	    b.setMinSize(100, 100);
    	    v.setSpacing(10);
	    	Label l = new Label("please select a zombie");
	    	b.setOnAction(e -> {
	            Buttons.AttackButton(GameView.current_hero, GameView.current_zombie);
	        });
	    	v.getChildren().addAll(l,b);
	    	return v;
    }

}
