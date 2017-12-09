/*     */ package com.liang.util.hashmap;
/*     */ 
/*     */

import com.liang.util.PrimitiveHashBase;

import java.lang.reflect.Array;

/*     */

/*     */
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PrimitiveKeyBase
/*     */   extends PrimitiveHashBase
/*     */ {
/*     */   public PrimitiveKeyBase(int paramInt, double paramDouble, Class paramClass1, Class paramClass2)
/*     */   {
/*  63 */     super(paramInt, paramDouble, paramClass1);
/*  64 */     setValueArray(Array.newInstance(paramClass2, this.m_flagTable.length));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PrimitiveKeyBase(PrimitiveKeyBase paramPrimitiveKeyBase)
/*     */   {
/*  74 */     super(paramPrimitiveKeyBase);
/*  75 */     int i = paramPrimitiveKeyBase.m_flagTable.length;
/*  76 */     Class localClass = paramPrimitiveKeyBase.getValueArray().getClass().getComponentType();
/*  77 */     Object localObject = Array.newInstance(localClass, i);
/*  78 */     System.arraycopy(paramPrimitiveKeyBase.getValueArray(), 0, localObject, 0, i);
/*  79 */     setValueArray(localObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract Object getValueArray();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void setValueArray(Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void restructure(boolean[] paramArrayOfBoolean, Object paramObject1, Object paramObject2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void reallocate(int paramInt)
/*     */   {
/* 129 */     boolean[] arrayOfBoolean = this.m_flagTable;
/* 130 */     this.m_flagTable = new boolean[paramInt];
/* 131 */     Object localObject1 = getKeyArray();
/* 132 */     Class localClass = localObject1.getClass().getComponentType();
/* 133 */     setKeyArray(Array.newInstance(localClass, paramInt));
/* 134 */     Object localObject2 = getValueArray();
/* 135 */     localClass = localObject2.getClass().getComponentType();
/* 136 */     setValueArray(Array.newInstance(localClass, paramInt));
/*     */     
/*     */ 
/* 139 */     restructure(arrayOfBoolean, localObject1, localObject2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 150 */     super.clear();
/* 151 */     Object localObject = getValueArray();
/* 152 */     if (!localObject.getClass().getComponentType().isPrimitive()) {
/* 153 */       Object[] arrayOfObject = (Object[])localObject;
/* 154 */       for (int i = 0; i < arrayOfObject.length; i++) {
/* 155 */         arrayOfObject[i] = null;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


