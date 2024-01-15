package net.PeytonPlayz585.shadow.input;

import org.teavm.jso.JSBody;

public class Controller {

    @JSBody(params = {"i"}, script = "return isKeyDown(i);")
    private static native boolean isKeyDown2(int i);

    @JSBody(params = {"i"}, script = "return isPressed(i);")
    private static native boolean isPressed2(int i);

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

    public static boolean isKeyDown(String i) {
        if(keyboardToGamepad(i) == -1) {
            return false;
        }
        return isKeyDown2(keyboardToGamepad(i));
    }

    public static boolean isPressed(String i) {
        if(keyboardToGamepad(i) == -1) {
            return false;
        }
        return isPressed2(keyboardToGamepad(i));
    }

    public static int getEventDWheel() {
        if(isPressed2(4)) {
            return -1;
        } else if(isPressed2(5)) {
            return 1;
        }

        return 0;
    }

    public static int keyboardToGamepad(String s) {
        if(s.contains("attack")) {
            return 7;
        } else if(s.contains("use")) {
            return 6;
        } else if(s.contains("drop")) {
            return 13;
        } else if(s.contains("togglePerspective")) {
            return 12;
        } else if(s.contains("inventory")) {
            return 3;
        } else if(s.contains("jump")) {
            return 0;
        } else if(s.contains("sneak")) {
            return 1;
        } else if(s.contains("sprint")) {
            return 10;
        }

        return -1;
    }
}