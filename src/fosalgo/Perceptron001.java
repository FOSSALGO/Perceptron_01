package fosalgo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Perceptron001 {

    public static void main(String[] args) {
        //parameters
        double alpha = 1;//learning rate
        double theta = 0;//treshold

        //input dan bias
        // wo = bias; xo = constant = 1
        // Arraylist [input | target]
        ArrayList<int[]> datatraining = new ArrayList<>();

        int[] data1 = {1, 1, 1, 1};//[xo,x1,x2,t]
        datatraining.add(data1);
        int[] data2 = {1, 1, -1, -1};//[xo,x1,x2,t]
        datatraining.add(data2);
        int[] data3 = {1, -1, 1, -1};//[xo,x1,x2,t]
        datatraining.add(data3);
        int[] data4 = {1, -1, -1, -1};//[xo,x1,x2,t]
        datatraining.add(data4);

        //print data training
        System.out.println("-------------------------------------------------");
        System.out.println("DATA TRAINING");
        for (int[] data : datatraining) {
            System.out.print("[ ");
            for (int i = 0; i < data.length - 1; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print("W" + i + " = " + data[i]);
            }
            System.out.println(", T = " + data[data.length - 1] + " ]");
        }
        System.out.println("-------------------------------------------------");
        
        //PROSES TRAINING
        System.out.println("PROSES TRAINING");
        int size = datatraining.get(0).length-1;
        double[] w = new double[size];//array bobot atau model
        
        int epoch = 1;
        boolean konvergen = false;
        while(!konvergen){
            System.out.println("E P O C H " + epoch);
            int nValid = 0;
            for (int[] data : datatraining) {
                //set unit input
                int[] x = new int[size];
                for (int i = 0; i < x.length; i++) {
                    x[i] = data[i];
                }
                int t = data[data.length-1];
                
                //hitung net
                double net = 0;
                for (int i = 0; i < x.length; i++) {
                    double xi_wi = x[i] * w[i];
                    net = net + xi_wi;
                }
                
                //hitung nilai respon output (y = f(net)) menggunakan fungsi aktivasi
                int y =0;
                if (net > theta) {
                    y = 1;
                } else if (net >= -theta && net <= theta) {
                    y = 0;
                } else if (net < -theta) {
                    y = -1;
                }
                
                //cek apakah nilai y = target
                if (y == t) {
                    //tandai sebagi bobot yang sudah valid
                    nValid++;
                } else {
                    //update bobot
                    for (int i = 0; i < w.length; i++) {
                        double deltaW = alpha * t * x[i];
                        w[i] = w[i] + deltaW;//update nilai bobot
                    }
                }
                
            }
            
            //cek berapa input dan target yang sudah berhasil valid
            if(nValid == datatraining.size()){
                System.out.println("SEMUA POLA INPUT SUDAH DIKENALI");
                System.out.println("PROSES TRAINING SELESAI");
                konvergen = true;
                break;
            }else{
                epoch++;
            }
            
        }//end of while
        
        //BOBOT HASIL TRAINING
        System.out.println("MODEL");
        System.out.println(Arrays.toString(w));
        
        System.out.println("-------------------------------------------------");
        System.out.println("PENGENALAN POLA");
        int k = 1;
        while (true) {
            System.out.println("Test-"+k+" "); 
            Scanner sc = new Scanner(System.in);
            System.out.println("Masukkan Element INPUT dan TARGET");
            int[] x = new int[size];
            x[0]=1;
            for (int i = 1; i < size; i++) {
                System.out.print("x" + i + ": ");
                x[i] = sc.nextInt();
            }
            
            //hitung net
            double net = 0;
            for (int i = 0; i < size; i++) {
                net = net + (x[i]*w[i]);
            }
            
            //interpretasikan net sebagai output menggunakan fungsi aktivasi
            int y = 0;
            if (net > theta) {
                y = 1;
            } else if (net >= -theta && net <= theta) {
                y = 0;
            } else if (net < -theta) {
                y = -1;
            }
            
            System.out.println("POLA hasil recognize oleh Perceptron = "+y);
            
            //MENU
            System.out.println("---------------");
            System.out.println("MENU");
            System.out.println("[1] lanjutkan pengenalan pola");
            System.out.println("[2] BREAK");
            System.out.println("---------------");
            System.out.print("menu yang anda pilih: ");
            int menuTerpilih = sc.nextInt();
            
            if(menuTerpilih==1){
                k++;
                System.out.println("menu terpilih: lanjutkan pengenalan pola");         
            }else if(menuTerpilih==2){
                System.out.println("menu terpilih: BREAK");  
                System.out.println("PROSES PENGENALAN POLA selesai");
                break;                        
            }
            System.out.println("---------------");

        }

    }
}
