package com.gnaf.basic.innerclass.demo03;

/**
 * @author Derek
 * @date 2019/9/21 17:16
 */
public class MainController extends Controller {

    private boolean isShow;

    public class ShowEvent extends Event {

        public ShowEvent(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            System.out.println("Show Event");
        }
    }

}
