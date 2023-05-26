package gui;

import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HUD {
    public static Node hudHero(){
        VBox characterInfo = new VBox();
        characterInfo.setStyle("-fx-background-color: #937e7e;");
        characterInfo.setAlignment(Pos.TOP_CENTER);
        Label characterName = new Label("Name: " + Game.heroes.get(0).getName());
        Label characterHealth = new Label("HP: " + Game.heroes.get(0).getCurrentHp() + "/" + Game.heroes.get(0).getMaxHp());
        Label characterAttackDamage = new Label("Attack Damage: " + Game.heroes.get(0).getAttackDmg());
        Label characterNumberOfMoves = new Label("Number of moves: ");
        Label characterType = new Label("Type: ");
        characterName.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterHealth.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterAttackDamage.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterNumberOfMoves.setFont(Font.font("Verdana", FontWeight.BOLD, 10));
        characterType.setFont(Font.font("Verdana", FontWeight.BOLD, 10));

        characterInfo.getChildren().addAll(characterName, characterHealth, characterAttackDamage,
                characterNumberOfMoves, characterType);

        characterName.setTranslateY(-300);
        characterHealth.setTranslateY(-275);
        characterAttackDamage.setTranslateY(-250);
        characterNumberOfMoves.setTranslateY(-225);
        characterType.setTranslateY(-200);
        return characterInfo;
    }

}
