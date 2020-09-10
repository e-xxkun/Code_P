package test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class Test {

    private static EnumSet<PizzaStatus> undeliveredPizzaStatuses =
            EnumSet.of(PizzaStatus.ORDERED, PizzaStatus.READY);

    public PizzaStatus getStatus() {
        return status;
    }

    public void setStatus(PizzaStatus status) {
        this.status = status;
    }

    private PizzaStatus status;
    public enum PizzaStatus {
        ORDERED (5){
            @Override
            public boolean isOrdered() {
                return true;
            }
        },
        READY (2){
            @Override
            public boolean isReady() {
                return true;
            }
        },
        DELIVERED (0){
            @Override
            public boolean isDelivered() {
                return true;
            }
        };

        private int timeToDelivery;

        public boolean isOrdered() {return false;}

        public boolean isReady() {return false;}

        public boolean isDelivered(){return false;}

        public int getTimeToDelivery() {
            return timeToDelivery;
        }

        PizzaStatus (int timeToDelivery) {
            this.timeToDelivery = timeToDelivery;
        }
    }

    public boolean isDeliverable() {
        return this.status.isReady();
    }


    public void printTimeToDeliver() {
        System.out.println("Time to delivery is " +
                this.getStatus().getTimeToDelivery() + " days");
    }

    public static List<Test> getAllUndeliveredPizzas(List<Test> input) {
        return input.stream().filter(
                (s) -> undeliveredPizzaStatuses.contains(s.getStatus()))
                .collect(Collectors.toList());
    }

//    public void deliver() {
//        if (isDeliverable()) {
//            PizzaDeliverySystemConfiguration.getInstance().getDeliveryStrategy()
//                    .deliver(this);
//            this.setStatus(PizzaStatus.DELIVERED);
//        }
//    }

    public static void main(String[] args) {
//        Test testPz = new Test();
//        testPz.setStatus(Test.PizzaStatus.READY);
//        System.out.println(testPz.isDeliverable());
//        testPz.setStatus(PizzaStatus.ORDERED);
//        System.out.println(testPz.isDeliverable());

        List<Test> pzList = new ArrayList<>();
        Test pz1 = new Test();
        pz1.setStatus(Test.PizzaStatus.DELIVERED);

        Test pz2 = new Test();
        pz2.setStatus(Test.PizzaStatus.ORDERED);

        Test pz3 = new Test();
        pz3.setStatus(Test.PizzaStatus.ORDERED);

        Test pz4 = new Test();
        pz4.setStatus(Test.PizzaStatus.READY);

        pzList.add(pz1);
        pzList.add(pz2);
        pzList.add(pz3);
        pzList.add(pz4);

        List<Test> undeliveredPzs = Test.getAllUndeliveredPizzas(pzList);
        System.out.println(undeliveredPzs.size());
    }
}
