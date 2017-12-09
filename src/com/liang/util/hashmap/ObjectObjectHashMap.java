/*     */ package com.liang.util.hashmap;
/*     */ 
/*     */

import com.liang.util.SparseArrayIterator;

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
/*     */ public class ObjectObjectHashMap
/*     */   extends ObjectKeyBase
/*     */ {
/*     */   protected Object[] m_keyTable;
/*     */   protected Object[] m_valueTable;
/*     */   
/*     */   public ObjectObjectHashMap(int paramInt, double paramDouble, Object paramObject)
/*     */   {
/*  61 */     super(paramInt, paramDouble, Object.class, Object.class, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectObjectHashMap(int paramInt, double paramDouble)
/*     */   {
/*  73 */     this(paramInt, paramDouble, "base");
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
/*     */   public ObjectObjectHashMap(int paramInt, Object paramObject)
/*     */   {
/*  87 */     this(paramInt, 0.3D, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectObjectHashMap(int paramInt)
/*     */   {
/*  98 */     this(paramInt, 0.3D);
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
/*     */   public ObjectObjectHashMap(Object paramObject)
/*     */   {
/* 111 */     this(0, 0.3D, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectObjectHashMap()
/*     */   {
/* 119 */     this(0, 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectObjectHashMap(ObjectObjectHashMap paramObjectObjectHashMap)
/*     */   {
/* 129 */     super(paramObjectObjectHashMap);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Object[] getKeyArray()
/*     */   {
/* 141 */     return this.m_keyTable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void setKeyArray(Object paramObject)
/*     */   {
/* 153 */     this.m_keyTable = ((Object[])paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Object getValueArray()
/*     */   {
/* 165 */     return this.m_valueTable;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void setValueArray(Object paramObject)
/*     */   {
/* 177 */     this.m_valueTable = ((Object[])paramObject);
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
/*     */   protected final boolean reinsert(int paramInt)
/*     */   {
/* 192 */     Object localObject = this.m_keyTable[paramInt];
/* 193 */     this.m_keyTable[paramInt] = null;
/* 194 */     return assignSlot(localObject, this.m_valueTable[paramInt]) != paramInt;
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
/*     */   protected void restructure(Object paramObject1, Object paramObject2)
/*     */   {
/* 209 */     Object[] arrayOfObject1 = (Object[])paramObject1;
/* 210 */     Object[] arrayOfObject2 = (Object[])paramObject2;
/* 211 */     for (int i = 0; i < arrayOfObject1.length; i++) {
/* 212 */       if (arrayOfObject1[i] != null) {
/* 213 */         assignSlot(arrayOfObject1[i], arrayOfObject2[i]);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int assignSlot(Object paramObject1, Object paramObject2)
/*     */   {
/* 231 */     int i = freeSlot(standardSlot(paramObject1));
/* 232 */     this.m_keyTable[i] = paramObject1;
/* 233 */     this.m_valueTable[i] = paramObject2;
/* 234 */     return i;
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
/*     */   public Object add(Object paramObject1, Object paramObject2)
/*     */   {
/* 250 */     if (paramObject1 == null)
/* 251 */       throw new IllegalArgumentException("null key not supported");
/* 252 */     if (paramObject2 == null) {
/* 253 */       throw new IllegalArgumentException("null value not supported");
/*     */     }
/*     */     
/*     */ 
/* 257 */     ensureCapacity(this.m_entryCount + 1);
/* 258 */     int i = standardFind(paramObject1);
/* 259 */     if (i >= 0)
/*     */     {
/*     */ 
/* 262 */       Object localObject = this.m_valueTable[i];
/* 263 */       this.m_valueTable[i] = paramObject2;
/* 264 */       return localObject;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 269 */     this.m_entryCount += 1;
/* 270 */     i = -i - 1;
/* 271 */     this.m_keyTable[i] = paramObject1;
/* 272 */     this.m_valueTable[i] = paramObject2;
/* 273 */     return null;
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
/*     */   public final boolean containsKey(Object paramObject)
/*     */   {
/* 289 */     return standardFind(paramObject) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final Object get(Object paramObject)
/*     */   {
/* 300 */     int i = standardFind(paramObject);
/* 301 */     if (i >= 0) {
/* 302 */       return this.m_valueTable[i];
/*     */     }
/* 304 */     return null;
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
/*     */   public Object remove(Object paramObject)
/*     */   {
/* 318 */     int i = standardFind(paramObject);
/* 319 */     if (i >= 0) {
/* 320 */       Object localObject = this.m_valueTable[i];
/* 321 */       internalRemove(i);
/* 322 */       return localObject;
/*     */     }
/* 324 */     return null;
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
/*     */   public final Iterator valueIterator()
/*     */   {
/* 338 */     return SparseArrayIterator.buildIterator(this.m_valueTable);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 348 */     return new ObjectObjectHashMap(this);
/*     */   }
/*     */ }


