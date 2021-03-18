package com.fileskripsi.skripsi.CF_data

import kotlin.math.roundToInt

class CF_Val{
    var Cf1: Double =0.0
    var Cf2: Double =0.0
    var Cf3: Double =0.0
    var Cf4: Double =0.0
    var Cf5: Double =0.0
    var Cf6: Double =0.0
    var Cf7: Double =0.0
    var Cf8: Double =0.0
    var Cf9: Double =0.0
    var CF_rule :Double =0.0
    var CF_pakar :Double=0.0
    var CF_pakar1 :Double=0.0
    var CF_Pakar_data = mutableListOf<Double>()
    var nlistcfcombinerendah = ArrayList<Double>()
    var dataCombine: Double= 0.0
    var dataCombine1: Double= 0.0
    var dataCombine2: Double= 0.0
    var dataCombine3: Double= 0.0
    var dataCombine4: Double= 0.0
    var dataCombine5: Double= 0.0
    var dataCombine6: Double= 0.0
    var dataCombine7: Double= 0.0
    var dataCombine8: Double= 0.0

    fun CF_hitung1(){

        val listMB = listOf<Double>(0.8, 0.8, 0.8, 0.4, 0.8,0.6 , 0.6, 0.8, 0.8)
        val listMD = listOf<Double>(0.4, 0.4, 0.6, 0.4, 0.6,0.6 , 0.6, 0.8, 0.8)
        for (i in listMB.indices)
        {
            for (j in listMD.indices)
            {
                if (i == 0) {
                    CF_pakar = listMB[i] - listMD[j]
                    CF_pakar1 = CF_pakar.roundToInt().toDouble()
                    CF_Pakar_data.add(CF_pakar)
                }
            }
        }
    }
    fun Hitung_CF(cf1:Double,cf2: Double,cf3:Double,cf4: Double,cf5:Double,cf6: Double,cf7:Double,cf8: Double,cf9: Double){
        var x = listOf(cf1, cf2, cf3, cf4, cf5, cf6, cf7, cf8, cf9)
        CF_hitung1()
        for (i in x.indices) {
            if (i==0) {
                for (j in CF_Pakar_data.indices) {
                    CF_rule = x[j] * CF_Pakar_data[j]
                    nlistcfcombinerendah.add(CF_rule)
                }
            }
        }
        for (i in nlistcfcombinerendah.indices) {
            if (i == 0) {
                dataCombine =
                    nlistcfcombinerendah[0] + nlistcfcombinerendah[1] * (1 - nlistcfcombinerendah[0])
                dataCombine1 = nlistcfcombinerendah[1] + dataCombine * (1 - nlistcfcombinerendah[1])
                dataCombine2 =
                    nlistcfcombinerendah[2] + dataCombine1 * (1 - nlistcfcombinerendah[2])
                dataCombine3 =
                    nlistcfcombinerendah[3] + dataCombine2 * (1 - nlistcfcombinerendah[3])
                dataCombine4 =
                    nlistcfcombinerendah[4] + dataCombine3 * (1 - nlistcfcombinerendah[4])
                dataCombine5 =
                    nlistcfcombinerendah[5] + dataCombine4 * (1 - nlistcfcombinerendah[5])
                dataCombine6 =
                    nlistcfcombinerendah[6] + dataCombine5 * (1 - nlistcfcombinerendah[6])
                dataCombine7 =
                    nlistcfcombinerendah[7] + dataCombine6 * (1 - nlistcfcombinerendah[7])
                dataCombine8 =
                    nlistcfcombinerendah[8] + dataCombine7 * (1 - nlistcfcombinerendah[8])
            }
        }
        val X =  Cf_Class(dataCombine, dataCombine1, dataCombine2, dataCombine3, dataCombine4, dataCombine5, dataCombine6, dataCombine7, dataCombine8)
        val datalist = (X.cf1 + X.cf2 + X.cf3 + X.cf4 + X.cf5 + X.cf6 + X.cf7 + X.cf8 + X.cf9) / 9 * 100
        val data = datalist.roundToInt()
        println(" hasil Hitung :" + data)
        print("THe size " + x.size)
        println("\n CF PAKAR = "  + CF_Pakar_data)
        println("\n ISI data : $x")
        println(nlistcfcombinerendah)


    }






}