package com.neocoder.time_counter;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int val_move, val_xchg, val_loop, val_nop, val_exit;
        int A=0, B = 0, C=0, D=0;

        val_move = scanner.nextInt();
        val_xchg = scanner.nextInt();
        val_loop = scanner.nextInt();
        val_nop = scanner.nextInt();
        val_exit = scanner.nextInt();

        Deque<String> cmds = new LinkedList<>();

        int cmds_prfs = 0;// Количество исполнений программы
        int result = 0; // Время исполнения
        int loop_tmp = 0;

        String tmp = "tes";

        while (!Objects.equals(tmp, "EXIT")) {
            tmp = scanner.nextLine();
            if(!Objects.equals(tmp, "")){
                cmds.add(tmp);
            }
        }




        while (!cmds.isEmpty()) {
            if (cmds_prfs == 1400) {
                break;
            }


            if (Objects.equals(cmds.peek(), "EXIT")) {
                result = result + val_exit;
//                System.out.println("Res: "+result+", Exit: "+val_exit);

                break;
            }


            if (cmds.peek().contains("MOV")) {
                if (cmds.peek().charAt(6) != 'A' && cmds.peek().charAt(6) != 'B' && cmds.peek().charAt(6) != 'C' && cmds.peek().charAt(6) != 'D') {
                    cmds_prfs++;
                    result += val_move;
//                    if (cmds.peek().charAt(6) == 'C') {
//                        loop_tmp = C;
//                        System.out.println(loop_tmp);
//
//                    }
//                    System.out.println("Res: "+result+", Move: "+val_move);
                    char reg = cmds.peek().charAt(4);

                    int mov_val = Integer.parseInt(String.valueOf(cmds.peek().substring(6,cmds.peek().length())));

                    switch (reg) {
                        case 'A':
                            A = mov_val;
                            break;
                        case 'B':
                            B = mov_val;
                            break;
                        case 'C':
                            C = mov_val;
                            loop_tmp = C;
                            break;
                        case 'D':
                            D = mov_val;
                            break;
                    }

                }else
                {
                    cmds_prfs++;
                    result+=val_move;
//                    System.out.println("Res: "+result+", Move2: "+val_move);

                    char mov_two_a = cmds.peek().charAt(4);
                    char mov_two_b = cmds.peek().charAt(6);
                    switch (mov_two_a){
                        case 'A':
                        {
                            switch (mov_two_b){
                                case 'B': A = B;break;
                                case 'C': A = C; break;
                                case 'D': A = D;break;
                                default:;
                            }
                        }
                        break;

                        case 'B':
                        {
                            switch (mov_two_b){
                                case 'A': B = A;break;
                                case 'C': B = C; break;
                                case 'D': B = D;break;
                                default:;
                            }
                        }
                        break;
                        case 'C':
                        {
                            switch (mov_two_b){
                                case 'A': C = A;break;
                                case 'B': C = B; break;
                                case 'D': C = D;break;
                                default:;
                            }
                        }
                        break;
                        case 'D':
                        {
                            switch (mov_two_b){
                                case 'A': D = A;break;
                                case 'B': D = B; break;
                                case 'C': D = C;break;
                                default:
                            }
                        }
                        break;
                    }

                }

            }

            if (cmds.peek().contains("XCHG"))
            {

                cmds_prfs++;
                result +=val_xchg;
//                System.out.println("Res: "+result+", Xchg: "+val_xchg);

                char fst = cmds.peek().charAt(5);
                char scd = cmds.peek().charAt(7);

                int temporary;
                switch (fst) {
                    case 'A':
                        switch (scd) {
                            case 'B':
                                temporary = B;
                                B = A;
                                A = temporary;
                                break;
                            case 'C':
                                temporary = C;
                                C = A;
                                A = temporary;
                                break;
                            case 'D':
                                temporary = D;
                                D = A;
                                A = temporary;
                                break;
                            default:;
                        }
                        break;
                    case 'B':
                        switch (scd) {
                            case 'A':
                                temporary = A;
                                A = B;
                                B = temporary;
                                break;
                            case 'C':
                                temporary = C;
                                C = B;
                                B = temporary;
                                break;
                            case 'D':
                                temporary = D;
                                D = B;
                                B = temporary;
                                break;
                            default:;
                        }
                        break;
                    case 'C':
                        switch (scd) {
                            case 'B':
                                temporary = B;
                                B = C;
                                C = temporary;
                                break;
                            case 'A':
                                temporary = A;
                                A = C;
                                C = temporary;
                                break;
                            case 'D':
                                temporary = D;
                                D = C;
                                C = temporary;
                                break;
                            default:;
                        }
                        break;
                    case 'D':
                        switch (scd) {
                            case 'B':
                                temporary = B;
                                B = D;
                                D = temporary;
                                break;
                            case 'C':
                                temporary = C;
                                C = D;
                                D = temporary;
                                break;
                            case 'A':
                                temporary = A;
                                A = D;
                                D = temporary;
                                break;
                            default:;
                        }
                        break;
                }
            }

//            System.out.println(cmds.peek().substring(0, cmds.peek().length()) + "len" + cmds.peek().substring(0, cmds.peek().length()).length());


            if (cmds.peek().contains(":")) {
                char number = cmds.peek().charAt(cmds.peek().length()-1);
                for (String i : cmds) {
                    System.out.println(i);
                    System.out.println("_____________");
                }
                if (cmds.peek().contains(":label"+number)) {
                    cmds.remove();
                }

                Queue<String> secondQueue = new LinkedList<>();
                System.out.println(number);
                System.out.println("DEBUG: the loop is : " + cmds.peek());
                while(!Objects.equals(cmds.peek(), "LOOP label" + number)) {
                    secondQueue.add(cmds.peek());

                    cmds.remove();
                }
                if (cmds.peek().contains("LOOP label"+number)) {
                    cmds.remove();
                }

                for (int i = 0; i<loop_tmp; i++) {
                    cmds_prfs++;
                    result+=val_loop;
//                    System.out.println(C);
//                    System.out.println("Res: "+result+", Loop: "+val_loop);

                    Queue<String> tmpQueue = new LinkedList<>();
                    tmpQueue.addAll(secondQueue);
                    while (!tmpQueue.isEmpty()) {
                        cmds.addFirst(tmpQueue.remove());
                    }
                }
//                for( String i : cmds) {
//                    System.out.println("DEBUG: the iteration is " + i);
//                }


//                System.out.println("DEBUG: THE number of iterations " + C);
            }
            if (cmds.peek().contains("NOP")) {
                cmds_prfs++;
                result+=val_nop;
//                System.out.println("Res: "+result+", Nop: "+val_nop);

//                System.out.println("DEBUG: Nop is working");

            }

            cmds.remove();

        }

        System.out.println(result);

    }
}
