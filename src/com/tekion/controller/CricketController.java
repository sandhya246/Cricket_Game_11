package com.tekion.controller;

import com.tekion.util.SeriesUtil;

public class CricketController
{
  public static void main(String[] args)
  {
      SeriesUtil seriesUtil= new SeriesUtil();
      seriesUtil.startSeries(1,"India",2,"England",3);
      System.out.println("series Completed");

  }
}
