/*     */ package com.liang.util.hashmap;
/*     */ 
/*     */

import com.liang.util.ObjectHashBase;
import com.liang.util.SparseArrayIterator;

import java.lang.reflect.Array;
import java.util.Iterator;

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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ObjectKeyBase
/*     */   extends ObjectHashBase
/*     */ {
/*     */   public ObjectKeyBase(int paramInt, double paramDouble, Class paramClass1, Class paramClass2, Object paramObject)
/*     */   {
/*  70 */     super(paramInt, paramDouble, paramClass1, paramObject);
/*  71 */     setValueArray(Array.newInstance(paramClass2, this.m_arraySize));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectKeyBase(ObjectKeyBase paramObjectKeyBase)
/*     */   {
/*  81 */     super(paramObjectKeyBase);
/*  82 */     Class localClass = paramObjectKeyBase.getValueArray().getClass().getComponentType();
/*  83 */     Object localObject = Array.newInstance(localClass, this.m_arraySize);
/*  84 */     System.arraycopy(paramObjectKeyBase.getValueArray(), 0, localObject, 0, this.m_arraySize);
/*  85 */     setValueArray(localObject);
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
/*     */   protected abstract void restructure(Object paramObject1, Object paramObject2);
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
/* 133 */     Object[] arrayOfObject = getKeyArray();
/* 134 */     Class localClass = arrayOfObject.getClass().getComponentType();
/* 135 */     setKeyArray(Array.newInstance(localClass, paramInt));
/* 136 */     Object localObject = getValueArray();
/* 137 */     localClass = localObject.getClass().getComponentType();
/* 138 */     setValueArray(Array.newInstance(localClass, paramInt));
/*     */     
/*     */ 
/* 141 */     restructure(arrayOfObject, localObject);
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
/*     */ 
/*     */   protected abstract boolean reinsert(int paramInt);
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
/*     */   protected void internalRemove(int paramInt)
/*     */   {
/* 170 */     Object[] arrayOfObject = getKeyArray();
/* 171 */     arrayOfObject[paramInt] = null;
/* 172 */     this.m_entryCount -= 1;
/* 173 */     while (arrayOfObject[(paramInt = stepSlot(paramInt))] != null)
/*     */     {
/*     */ 
/* 176 */       reinsert(paramInt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 189 */     super.clear();
/* 190 */     Object localObject = getValueArray();
/* 191 */     if (!localObject.getClass().getComponentType().isPrimitive()) {
/* 192 */       Object[] arrayOfObject = (Object[])localObject;
/* 193 */       for (int i = 0; i < arrayOfObject.length; i++) {
/* 194 */         arrayOfObject[i] = null;
/*     */       }
/*     */     }
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
/*     */   public final Iterator iterator()
/*     */   {
/* 209 */     return SparseArrayIterator.buildIterator((Object[])getKeyArray());
/*     */   }
/*     */ }


