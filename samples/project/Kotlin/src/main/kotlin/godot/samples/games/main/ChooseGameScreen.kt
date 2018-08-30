package godot.samples.games.main

import godot.*
import godot.core.NodePath

class ChooseGameScreen: Node() {

    lateinit var playDodgeButton: Button
    lateinit var playShmupButton: Button
    lateinit var backButton: Button
    lateinit var playCatchBallButton: Button
    lateinit var playFastFinishButton: Button

    override fun _ready() {
        playDodgeButton = Button from getNode(NodePath("MenuButtons/PlayDodgeButton"))
        playShmupButton = Button from getNode(NodePath("MenuButtons/PlayShmupButton"))
        playCatchBallButton = Button from getNode(NodePath("MenuButtons/PlayCatchBallButton"))
        playFastFinishButton = Button from getNode(NodePath("MenuButtons/PlayFastFinishButton"))
        backButton = Button from getNode(NodePath("MenuButtons/BackButton"))

        playDodgeButton.connect("pressed", this, "_onPlayDodgeButtonPressed")
        playShmupButton.connect("pressed", this, "_onPlayShmupButtonPressed")
        playCatchBallButton.connect("pressed", this, "_onPlayCatchBallButtonPressed")
        playFastFinishButton.connect("pressed", this, "_onPlayFastFinishButtonPressed")
        backButton.connect("pressed", this, "_onBackButtonPressed")
    }

    fun _onPlayDodgeButtonPressed() {
        getTree().changeScene("res://Games/dodge/Scenes/Main.tscn")
    }

    fun _onPlayShmupButtonPressed() {
        getTree().changeScene("res://Games/Shmup/Scenes/Stage.tscn")
    }

    fun _onPlayCatchBallButtonPressed() {
        getTree().changeScene("res://Games/CatchBall/Scenes/Stage.tscn")
    }

    fun _onPlayFastFinishButtonPressed() {
        getTree().changeScene("res://Games/FastFinish/Scenes/Stage.tscn")
    }

    fun _onBackButtonPressed() {
        getTree().changeScene("res://Games/Main/Scenes/MainScreen.tscn")
    }

}