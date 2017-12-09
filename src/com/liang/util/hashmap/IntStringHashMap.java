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
/*     */ public class IntStringHashMap
/*     */   extends PrimitiveKeyBase
/*     */ {
/*     */   protected int[] m_keyTable;
/*     */   protected String[] m_valueTable;
/*     */   
/*     */   public IntStringHashMap(int paramInt, double paramDouble)
/*     */   {
/*  58 */     super(paramInt, paramDouble, Integer.TYPE, String.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntStringHashMap(int paramInt)
/*     */   {
/*  69 */     this(paramInt, 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntStringHashMap()
/*     */   {
/*  77 */     this(0, 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntStringHashMap(IntStringHashMap paramIntStringHashMap)
/*     */   {
/*  87 */     super(paramIntStringHashMap);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final Object getKeyArray()
/*     */   {
/*  99 */     return this.m_keyTable;
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
/* 111 */     this.m_keyTable = ((int[])paramObject);
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
/* 123 */     return this.m_valueTable;
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
/* 135 */     this.m_valueTable = ((String[])paramObject);
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
/*     */   protected final boolean reinsert(int paramInt)
/*     */   {
/* 149 */     this.m_flagTable[paramInt] = false;
/* 150 */     return assignSlot(this.m_keyTable[paramInt], this.m_valueTable[paramInt]) != paramInt;
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
/*     */   protected void restructure(boolean[] paramArrayOfBoolean, Object paramObject1, Object paramObject2)
/*     */   {
/* 166 */     int[] arrayOfInt = (int[])paramObject1;
/* 167 */     String[] arrayOfString = (String[])paramObject2;
/* 168 */     for (int i = 0; i < paramArrayOfBoolean.length; i++) {
/* 169 */       if (paramArrayOfBoolean[i] != false) {
/* 170 */         assignSlot(arrayOfInt[i], arrayOfString[i]);
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
/*     */   protected final int computeSlot(int paramInt)
/*     */   {
/* 183 */     return (paramInt * 517 & 0x7FFFFFFF) % this.m_flagTable.length;
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
/*     */   protected int assignSlot(int paramInt, String paramString)
/*     */   {
/* 199 */     int i = freeSlot(computeSlot(paramInt));
/* 200 */     this.m_flagTable[i] = true;
/* 201 */     this.m_keyTable[i] = paramInt;
/* 202 */     this.m_valueTable[i] = paramString;
/* 203 */     return i;
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
/*     */   public String add(int paramInt, String paramString)
/*     */   {
/* 217 */     ensureCapacity(this.m_entryCount + 1);
/* 218 */     int i = internalFind(paramInt);
/* 219 */     if (i >= 0) {
/* 220 */       String str = this.m_valueTable[i];
/* 221 */       this.m_valueTable[i] = paramString;
/* 222 */       return str;
/*     */     }
/* 224 */     this.m_entryCount += 1;
/* 225 */     i = -i - 1;
/* 226 */     this.m_flagTable[i] = true;
/* 227 */     this.m_keyTable[i] = paramInt;
/* 228 */     this.m_valueTable[i] = paramString;
/* 229 */     return null;
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
/*     */   protected final int internalFind(int paramInt)
/*     */   {
/* 242 */     int i = computeSlot(paramInt);
/* 243 */     while (this.m_flagTable[i] != false) {
/* 244 */       if (paramInt == this.m_keyTable[i]) {
/* 245 */         return i;
/*     */       }
/* 247 */       i = stepSlot(i);
/*     */     }
/* 249 */     return -i - 1;
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
/*     */   public final boolean containsKey(int paramInt)
/*     */   {
/* 262 */     return internalFind(paramInt) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final String get(int paramInt)
/*     */   {
/* 273 */     int i = internalFind(paramInt);
/* 274 */     if (i >= 0) {
/* 275 */       return this.m_valueTable[i];
/*     */     }
/* 277 */     return null;
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
/*     */   public String remove(int paramInt)
/*     */   {
/* 290 */     int i = internalFind(paramInt);
/* 291 */     if (i >= 0) {
/* 292 */       String str = this.m_valueTable[i];
/* 293 */       this.m_flagTable[i] = false;
/* 294 */       this.m_entryCount -= 1;
/* 295 */       while (this.m_flagTable[(i = stepSlot(i))] != false) {
/* 296 */         reinsert(i);
/*     */       }
/* 298 */       return str;
/*     */     }
/* 300 */     return null;
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
/* 314 */     return SparseArrayIterator.buildIterator(this.m_valueTable);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 324 */     return new IntStringHashMap(this);
/*     */   }
/*     */ }


