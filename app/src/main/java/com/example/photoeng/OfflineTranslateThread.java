package com.example.photoeng;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OfflineTranslateThread extends Thread{

    public  String[] linesArrayA;
    public String[] linesArrayB;
    public String[] linesArrayC;
    public String[] linesArrayD;
    public String[] linesArrayE;
    public String[] linesArrayF;
    public String[] linesArrayG;
    public String[] linesArrayH;
    public String[] linesArrayI;
    public String[] linesArrayJ;
    public String[] linesArrayK;
    public String[] linesArrayL;
    public String[] linesArrayM;
    public String[] linesArrayN;
    public String[] linesArrayO;
    public String[] linesArrayP;
    public String[] linesArrayQ;
    public String[] linesArrayR;
    public String[] linesArrayS;
    public String[] linesArrayT;
    public String[] linesArrayU;
    public String[] linesArrayV;
    public String[] linesArrayW;
    public String[] linesArrayX;
    public String[] linesArrayY;
    public String[] linesArrayZ;
    public Context context;
    public String Answer;
    //@Override
    public void initialize() {
        //Scanning files
        try {
            InputStream fileA = context.getResources().openRawResource(R.raw.a_eng_rus);
            BufferedReader readerA = new BufferedReader(new InputStreamReader(fileA));
            linesArrayA = new String[9156];
            for (int i = 0; i < 9156; i++) {
                linesArrayA[i] = readerA.readLine();
            }
            fileA.close();
            readerA.close();

            InputStream fileB = context.getResources().openRawResource(R.raw.b_eng_rus);
            BufferedReader readerB = new BufferedReader(new InputStreamReader(fileB));
            linesArrayB = new String[4613];
            for (int q = 0; q < 4613; q++) {
                linesArrayB[q] = readerB.readLine();
            }
            fileB.close();
            readerB.close();

            InputStream fileC = context.getResources().openRawResource(R.raw.c_eng_rus);
            BufferedReader readerC = new BufferedReader(new InputStreamReader(fileC));
            linesArrayC = new String[8416];
            for (int i = 0; i < 8416; i++) {
                linesArrayC[i] = readerC.readLine();
            }
            fileC.close();
            readerC.close();

            InputStream fileD = context.getResources().openRawResource(R.raw.d_eng_rus);
            BufferedReader readerD = new BufferedReader(new InputStreamReader(fileD));
            linesArrayD = new String[5601];
            for (int i = 0; i < 5601; i++) {
                linesArrayD[i] = readerD.readLine();
            }
            fileD.close();
            readerD.close();

            InputStream fileE = context.getResources().openRawResource(R.raw.e_eng_rus);
            BufferedReader readerE = new BufferedReader(new InputStreamReader(fileE));
            linesArrayE = new String[3818];
            for (int i = 0; i < 3818; i++) {
                linesArrayE[i] = readerE.readLine();
            }
            fileE.close();
            readerE.close();

            InputStream fileF = context.getResources().openRawResource(R.raw.f_eng_rus);
            BufferedReader readerF = new BufferedReader(new InputStreamReader(fileF));
            linesArrayF = new String[5601];
            for (int i = 0; i < 3571; i++) {
                linesArrayF[i] = readerF.readLine();
            }
            fileF.close();
            readerF.close();

            InputStream fileG =context.getResources().openRawResource(R.raw.g_eng_rus);
            BufferedReader readerG = new BufferedReader(new InputStreamReader(fileG));
            linesArrayG = new String[2263];
            for (int i = 0; i < 2263; i++) {
                linesArrayG[i] = readerG.readLine();
            }
            fileG.close();
            readerG.close();

            InputStream fileH = context.getResources().openRawResource(R.raw.h_eng_rus);
            BufferedReader readerH = new BufferedReader(new InputStreamReader(fileH));
            linesArrayH = new String[2689];
            for (int i = 0; i < 2689; i++) {
                linesArrayH[i] = readerH.readLine();
            }
            fileH.close();
            readerH.close();

            InputStream fileI = context.getResources().openRawResource(R.raw.i_eng_rus);
            BufferedReader readerI = new BufferedReader(new InputStreamReader(fileI));
            linesArrayI = new String[4003];
            for (int i = 0; i < 4003; i++) {
                linesArrayI[i] = readerI.readLine();
            }
            fileI.close();
            readerI.close();

            InputStream fileJ = context.getResources().openRawResource(R.raw.j_eng_rus);
            BufferedReader readerJ = new BufferedReader(new InputStreamReader(fileJ));
            linesArrayJ = new String[591];
            for (int i = 0; i < 591; i++) {
                linesArrayJ[i] = readerJ.readLine();
            }
            fileJ.close();
            readerJ.close();

            InputStream fileK = context.getResources().openRawResource(R.raw.k_eng_rus);
            BufferedReader readerK = new BufferedReader(new InputStreamReader(fileK));
            linesArrayK = new String[514];
            for (int i = 0; i < 514; i++) {
                linesArrayK[i] = readerK.readLine();
            }
            fileK.close();
            readerK.close();

            InputStream fileL = context.getResources().openRawResource(R.raw.l_eng_rus);
            BufferedReader readerL = new BufferedReader(new InputStreamReader(fileL));
            linesArrayL = new String[2641];
            for (int i = 0; i < 2641; i++) {
                linesArrayL[i] = readerL.readLine();
            }
            fileL.close();
            readerL.close();

            InputStream fileM = context.getResources().openRawResource(R.raw.m_eng_rus);
            BufferedReader readerM = new BufferedReader(new InputStreamReader(fileM));
            linesArrayM = new String[4551];
            for (int i = 0; i < 4551; i++) {
                linesArrayM[i] = readerM.readLine();
            }
            fileM.close();
            readerM.close();

            InputStream fileN = context.getResources().openRawResource(R.raw.n_eng_rus);
            BufferedReader readerN = new BufferedReader(new InputStreamReader(fileN));
            linesArrayN = new String[1787];
            for (int i = 0; i < 1787; i++) {
                linesArrayN[i] = readerN.readLine();
            }
            fileN.close();
            readerN.close();

            InputStream fileO = context.getResources().openRawResource(R.raw.o_eng_rus);
            BufferedReader readerO = new BufferedReader(new InputStreamReader(fileO));
            linesArrayO = new String[2215];
            for (int i = 0; i < 2215; i++) {
                linesArrayO[i] = readerO.readLine();
            }
            fileO.close();
            readerO.close();

            InputStream fileP = context.getResources().openRawResource(R.raw.p_eng_rus);
            BufferedReader readerP = new BufferedReader(new InputStreamReader(fileP));
            linesArrayP = new String[10006];
            for (int i = 0; i < 10006; i++) {
                linesArrayP[i] = readerP.readLine();
            }
            fileP.close();
            readerP.close();

            InputStream fileQ = context.getResources().openRawResource(R.raw.q_eng_rus);
            BufferedReader readerQ = new BufferedReader(new InputStreamReader(fileQ));
            linesArrayQ = new String[613];
            for (int i = 0; i < 613; i++) {
                linesArrayQ[i] = readerQ.readLine();
            }
            fileQ.close();
            readerQ.close();

            InputStream fileR =context.getResources().openRawResource(R.raw.r_eng_rus);
            BufferedReader readerR = new BufferedReader(new InputStreamReader(fileR));
            linesArrayR = new String[4947];
            for (int i = 0; i < 4947; i++) {
                linesArrayR[i] = readerR.readLine();
            }
            fileR.close();
            readerR.close();

            InputStream fileS = context.getResources().openRawResource(R.raw.s_eng_rus);
            BufferedReader readerS = new BufferedReader(new InputStreamReader(fileS));
            linesArrayS = new String[16305];
            for (int i = 0; i < 16305; i++) {
                linesArrayS[i] = readerS.readLine();
            }
            fileS.close();
            readerS.close();

            InputStream fileT = context.getResources().openRawResource(R.raw.t_eng_rus);
            BufferedReader readerT = new BufferedReader(new InputStreamReader(fileT));
            linesArrayT = new String[5993];
            for (int i = 0; i < 5993; i++) {
                linesArrayT[i] = readerT.readLine();
            }
            fileT.close();
            readerT.close();

            InputStream fileU = context.getResources().openRawResource(R.raw.u_eng_rus);
            BufferedReader readerU = new BufferedReader(new InputStreamReader(fileU));
            linesArrayU = new String[3421];
            for (int i = 0; i < 3421; i++) {
                linesArrayU[i] = readerU.readLine();
            }
            fileU.close();
            readerU.close();

            InputStream fileV = context.getResources().openRawResource(R.raw.v_eng_rus);
            BufferedReader readerV = new BufferedReader(new InputStreamReader(fileV));
            linesArrayV = new String[1311];
            for (int i = 0; i < 1311; i++) {
                linesArrayV[i] = readerV.readLine();
            }
            fileV.close();
            readerV.close();

            InputStream fileW = context.getResources().openRawResource(R.raw.w_eng_rus);
            BufferedReader readerW = new BufferedReader(new InputStreamReader(fileW));
            linesArrayW = new String[3071];
            for (int i = 0; i < 3071; i++) {
                linesArrayW[i] = readerW.readLine();
            }
            fileW.close();
            readerW.close();

            InputStream fileX =context.getResources().openRawResource(R.raw.x_eng_rus);
            BufferedReader readerX = new BufferedReader(new InputStreamReader(fileX));
            linesArrayX = new String[53];
            for (int i = 0; i < 53; i++) {
                linesArrayX[i] = readerX.readLine();
            }
            fileX.close();
            readerX.close();

            InputStream fileY = context.getResources().openRawResource(R.raw.y_eng_rus);
            BufferedReader readerY = new BufferedReader(new InputStreamReader(fileY));
            linesArrayY = new String[301];
            for (int i = 0; i < 301; i++) {
                linesArrayY[i] = readerY.readLine();
            }
            fileY.close();
            readerY.close();

            InputStream fileZ = context.getResources().openRawResource(R.raw.z_eng_rus);
            BufferedReader readerZ = new BufferedReader(new InputStreamReader(fileZ));
            linesArrayZ = new String[234];
            for (int i = 0; i < 234; i++) {
                linesArrayZ[i] = readerZ.readLine();
            }
            fileZ.close();
            readerZ.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void translationNoInternet(String word) {

        char[] wordArray = word.toCharArray();
        if (wordArray[0] == 'a') {
            try {
                for (int i = 0; i < linesArrayA.length; i++) {
                    if (word.equals(linesArrayA[i])) {
                        Answer = linesArrayA[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'b') {
            try {
                for (int i = 0; i < linesArrayB.length; i++) {
                    if (word.equals(linesArrayB[i])) {
                        Answer = linesArrayB[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'c') {
            try {
                for (int i = 0; i < linesArrayC.length; i++) {
                    if (word.equals(linesArrayC[i])) {
                        Answer = linesArrayC[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'd') {
            try {
                for (int i = 0; i < linesArrayD.length; i++) {
                    if (word.equals(linesArrayD[i])) {
                        Answer = linesArrayD[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'e') {
            try {
                for (int i = 0; i < linesArrayE.length; i++) {
                    if (word.equals(linesArrayE[i])) {
                        Answer = linesArrayE[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'f') {
            try {
                for (int i = 0; i < linesArrayF.length; i++) {
                    if (word.equals(linesArrayF[i])) {
                        Answer = linesArrayF[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'g') {
            try {
                for (int i = 0; i < linesArrayG.length; i++) {
                    if (word.equals(linesArrayG[i])) {
                        Answer = linesArrayG[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'h') {
            try {
                for (int i = 0; i < linesArrayH.length; i++) {
                    if (word.equals(linesArrayH[i])) {
                        Answer = linesArrayH[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'i') {
            try {
                for (int i = 0; i < linesArrayI.length; i++) {
                    if (word.equals(linesArrayI[i])) {
                        Answer = linesArrayI[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'j') {
            try {
                for (int i = 0; i < linesArrayJ.length; i++) {
                    if (word.equals(linesArrayJ[i])) {
                        Answer = linesArrayJ[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'k') {
            try {
                for (int i = 0; i < linesArrayK.length; i++) {
                    if (word.equals(linesArrayK[i])) {
                        Answer = linesArrayK[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'l') {
            try {
                for (int i = 0; i < linesArrayL.length; i++) {
                    if (word.equals(linesArrayL[i])) {
                        Answer = linesArrayL[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'm') {
            try {
                for (int i = 0; i < linesArrayM.length; i++) {
                    if (word.equals(linesArrayM[i])) {
                        Answer = linesArrayM[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'n') {
            try {
                for (int i = 0; i < linesArrayN.length; i++) {
                    if (word.equals(linesArrayN[i])) {
                        Answer = linesArrayN[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'o') {
            try {
                for (int i = 0; i < linesArrayO.length; i++) {
                    if (word.equals(linesArrayO[i])) {
                        Answer = linesArrayO[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'p') {
            try {
                for (int i = 0; i < linesArrayP.length; i++) {
                    if (word.equals(linesArrayP[i])) {
                        Answer = linesArrayP[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'q') {
            try {
                for (int i = 0; i < linesArrayQ.length; i++) {
                    if (word.equals(linesArrayQ[i])) {
                        Answer = linesArrayQ[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'r') {
            try {
                for (int i = 0; i < linesArrayR.length; i++) {
                    if (word.equals(linesArrayR[i])) {
                        Answer = linesArrayR[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 's') {
            try {
                for (int i = 0; i < linesArrayS.length; i++) {
                    if (word.equals(linesArrayS[i])) {
                        Answer = linesArrayS[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 't') {
            try {
                for (int i = 0; i < linesArrayT.length; i++) {
                    if (word.equals(linesArrayT[i])) {
                        Answer = linesArrayT[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'u') {
            try {
                for (int i = 0; i < linesArrayU.length; i++) {
                    if (word.equals(linesArrayU[i])) {
                        Answer = linesArrayU[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'v') {
            try {
                for (int i = 0; i < linesArrayV.length; i++) {
                    if (word.equals(linesArrayV[i])) {
                        Answer = linesArrayV[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'w') {
            try {
                for (int i = 0; i < linesArrayW.length; i++) {
                    if (word.equals(linesArrayW[i])) {
                        Answer = linesArrayW[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'x') {
            try {
                for (int i = 0; i < linesArrayX.length; i++) {
                    if (word.equals(linesArrayX[i])) {
                        Answer = linesArrayX[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'y') {
            try {
                for (int i = 0; i < linesArrayY.length; i++) {
                    if (word.equals(linesArrayY[i])) {
                        Answer = linesArrayY[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (wordArray[0] == 'z') {
            try {
                for (int i = 0; i < linesArrayZ.length; i++) {
                    if (word.equals(linesArrayZ[i])) {
                        Answer = linesArrayA[i+1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public String getAnswer() {
        return Answer;
    }
}
