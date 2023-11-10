package net.PeytonPlayz585.shadow;

import org.teavm.jso.JSBody;

public class Controller {

    @JSBody(params = {}, script = "return isWalkingForward();")
    public static native boolean isWalkingForward();
    
    @JSBody(params = {}, script = "return isWalkingBackward();")
    public static native boolean isWalkingBackward();

    @JSBody(params = {}, script = "return isWalkingLeft();")
    public static native boolean isWalkingLeft();

    @JSBody(params = {}, script = "return isWalkingRight();")
    public static native boolean isWalkingRight();

    @JSBody(params = {}, script = "return getCameraX();")
    public static native int getCameraX();

    @JSBody(params = {}, script = "return getCameraY();")
    public static native int getCameraY();

    @JSBody(params = {}, script = "return jump;")
    public static native boolean isJumping();

    @JSBody(params = {}, script = "return crouch;")
    public static native boolean isSneaking();

    @JSBody(params = {}, script = "updateController();")
    public static native void controllerTick();

    @JSBody(params = {}, script = "jump = false;")
    public static native void unpressJump();

    @JSBody(params = {}, script = "crouch = false;")
    public static native void unpressCrouch();

    @JSBody(params = {"buttonID"}, script = "return isKeyDown(buttonID);")
    public static native boolean isButtonPressed(int buttonID);
}