/*     */ package com.liang.util.hashmap;
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
/*     */ public class StringIntHashMap
/*     */   extends ObjectKeyBase
/*     */ {
/*     */   public static final int DEFAULT_NOT_FOUND = Integer.MIN_VALUE;
/*     */   protected String[] m_keyTable;
/*     */   protected int[] m_valueTable;
/*     */   protected int m_notFoundValue;
/*     */   
/*     */   public StringIntHashMap(int paramInt1, double paramDouble, int paramInt2, Object paramObject)
/*     */   {
/*  64 */     super(paramInt1, paramDouble, String.class, Integer.TYPE, paramObject);
/*  65 */     this.m_notFoundValue = paramInt2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringIntHashMap(int paramInt, double paramDouble)
/*     */   {
/*  77 */     this(paramInt, paramDouble, Integer.MIN_VALUE, "base");
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
/*     */   public StringIntHashMap(int paramInt, Object paramObject)
/*     */   {
/*  91 */     this(paramInt, 0.3D, Integer.MIN_VALUE, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringIntHashMap(int paramInt)
/*     */   {
/* 102 */     this(paramInt, 0.3D);
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
/*     */   public StringIntHashMap(Object paramObject)
/*     */   {
/* 115 */     this(0, 0.3D, Integer.MIN_VALUE, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringIntHashMap()
/*     */   {
/* 123 */     this(0, 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StringIntHashMap(StringIntHashMap paramStringIntHashMap)
/*     */   {
/* 133 */     super(paramStringIntHashMap);
/* 134 */     this.m_notFoundValue = paramStringIntHashMap.m_notFoundValue;
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
/* 146 */     return this.m_keyTable;
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
/* 158 */     this.m_keyTable = ((String[])paramObject);
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
/* 170 */     return this.m_valueTable;
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
/* 182 */     this.m_valueTable = ((int[])paramObject);
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
/* 197 */     String str = this.m_keyTable[paramInt];
/* 198 */     this.m_keyTable[paramInt] = null;
/* 199 */     return assignSlot(str, this.m_valueTable[paramInt]) != paramInt;
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
/* 214 */     String[] arrayOfString = (String[])paramObject1;
/* 215 */     int[] arrayOfInt = (int[])paramObject2;
/* 216 */     for (int i = 0; i < arrayOfString.length; i++) {
/* 217 */       if (arrayOfString[i] != null) {
/* 218 */         assignSlot(arrayOfString[i], arrayOfInt[i]);
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
/*     */   protected int assignSlot(String paramString, int paramInt)
/*     */   {
/* 236 */     int i = freeSlot(standardSlot(paramString));
/* 237 */     this.m_keyTable[i] = paramString;
/* 238 */     this.m_valueTable[i] = paramInt;
/* 239 */     return i;
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
/*     */   public int add(String paramString, int paramInt)
/*     */   {
/* 255 */     if (paramString == null)
/* 256 */       throw new IllegalArgumentException("null key not supported");
/* 257 */     if (paramInt == this.m_notFoundValue) {
/* 258 */       throw new IllegalArgumentException("value matching not found return not supported");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 263 */     ensureCapacity(this.m_entryCount + 1);
/* 264 */     int i = standardFind(paramString);
/* 265 */     if (i >= 0)
/*     */     {
/*     */ 
/* 268 */       int j = this.m_valueTable[i];
/* 269 */       this.m_valueTable[i] = paramInt;
/* 270 */       return j;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 275 */     this.m_entryCount += 1;
/* 276 */     i = -i - 1;
/* 277 */     this.m_keyTable[i] = paramString;
/* 278 */     this.m_valueTable[i] = paramInt;
/* 279 */     return this.m_notFoundValue;
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
/*     */   public final boolean containsKey(String paramString)
/*     */   {
/* 295 */     return standardFind(paramString) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int get(String paramString)
/*     */   {
/* 306 */     int i = standardFind(paramString);
/* 307 */     if (i >= 0) {
/* 308 */       return this.m_valueTable[i];
/*     */     }
/* 310 */     return this.m_notFoundValue;
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
/*     */   public int remove(String paramString)
/*     */   {
/* 324 */     int i = standardFind(paramString);
/* 325 */     if (i >= 0) {
/* 326 */       int j = this.m_valueTable[i];
/* 327 */       internalRemove(i);
/* 328 */       return j;
/*     */     }
/* 330 */     return this.m_notFoundValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 341 */     return new StringIntHashMap(this);
/*     */   }
/*     */ }


