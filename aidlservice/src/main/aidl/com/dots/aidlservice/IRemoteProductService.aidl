// IRemoteProductService.aidl
package com.dots.aidlservice;

// Declare any non-default types here with import statements

interface ServerAidlInterface {
          void addProduct(String name,int quantity,float cost);
                String getProduct(String name);
//                Product getProduct(String name);
}
