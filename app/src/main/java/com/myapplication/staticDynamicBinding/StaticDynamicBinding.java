package com.myapplication.staticDynamicBinding;

public class StaticDynamicBinding {

    public static void main(String[] args) {

        BMW bmw = new BMW();
        bmw.driveCar();

        Car car = new Car();
        car.drive();
        car.drive("Abhinav");


        Car car1 = new HondaCity();

        car1.drive();
        car1.drive("Avinash");
        HondaCity hondaCity =  new HondaCity();
        hondaCity.drive();
    }

    static class  Car {
        void drive(){
            System.out.println("Car is running");
        }

        void drive(String driverName){
            System.out.println(driverName + " is running the car");
        }
    }

    static class HondaCity extends Car{
        @Override
         void drive(){
            System.out.println("Hond Cit Car is running");
        }

        /*@Override
        void drive(String driverName){
            System.out.println(driverName + " is running the Honda City car");
        }*/
    }

    static class BMW {
        public void driveCar(){
            drive();
        }

        private void  drive(){

            System.out.println("BMW  Car is running");
        }
    }

}
