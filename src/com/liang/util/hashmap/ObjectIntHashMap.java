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
/*     */ public class ObjectIntHashMap
/*     */   extends ObjectKeyBase
/*     */ {
/*     */   public static final int DEFAULT_NOT_FOUND = Integer.MIN_VALUE;
/*     */   protected Object[] m_keyTable;
/*     */   protected int[] m_valueTable;
/*     */   protected int m_notFoundValue;
/*     */   
/*     */   public ObjectIntHashMap(int paramInt1, double paramDouble, int paramInt2, Object paramObject)
/*     */   {
/*  64 */     super(paramInt1, paramDouble, Object.class, Integer.TYPE, paramObject);
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
/*     */   public ObjectIntHashMap(int paramInt, double paramDouble)
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
/*     */   public ObjectIntHashMap(int paramInt, Object paramObject)
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
/*     */   public ObjectIntHashMap(int paramInt)
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
/*     */   public ObjectIntHashMap(Object paramObject)
/*     */   {
/* 115 */     this(0, 0.3D, Integer.MIN_VALUE, paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectIntHashMap()
/*     */   {
/* 123 */     this(0, 0.3D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectIntHashMap(ObjectIntHashMap paramObjectIntHashMap)
/*     */   {
/* 133 */     super(paramObjectIntHashMap);
/* 134 */     this.m_notFoundValue = paramObjectIntHashMap.m_notFoundValue;
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
/* 158 */     this.m_keyTable = ((Object[])paramObject);
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
/* 197 */     Object localObject = this.m_keyTable[paramInt];
/* 198 */     this.m_keyTable[paramInt] = null;
/* 199 */     return assignSlot(localObject, this.m_valueTable[paramInt]) != paramInt;
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
/* 214 */     Object[] arrayOfObject = (Object[])paramObject1;
/* 215 */     int[] arrayOfInt = (int[])paramObject2;
/* 216 */     for (int i = 0; i < arrayOfObject.length; i++) {
/* 217 */       if (arrayOfObject[i] != null) {
/* 218 */         assignSlot(arrayOfObject[i], arrayOfInt[i]);
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
/*     */   protected int assignSlot(Object paramObject, int paramInt)
/*     */   {
/* 236 */     int i = freeSlot(standardSlot(paramObject));
/* 237 */     this.m_keyTable[i] = paramObject;
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
/*     */   public int add(Object paramObject, int paramInt)
/*     */   {
/* 255 */     if (paramObject == null)
/* 256 */       throw new IllegalArgumentException("null key not supported");
/* 257 */     if (paramInt == this.m_notFoundValue) {
/* 258 */       throw new IllegalArgumentException("value matching not found return not supported");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 263 */     ensureCapacity(this.m_entryCount + 1);
/* 264 */     int i = standardFind(paramObject);
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
/* 277 */     this.m_keyTable[i] = paramObject;
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
/*     */   public final boolean containsKey(Object paramObject)
/*     */   {
/* 295 */     return standardFind(paramObject) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public final int get(Object paramObject)
/*     */   {
/* 306 */     int i = standardFind(paramObject);
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
/*     */   public int remove(Object paramObject)
/*     */   {
/* 323 */     int i = standardFind(paramObject);
/* 324 */     if (i >= 0) {
/* 325 */       int j = this.m_valueTable[i];
/* 326 */       internalRemove(i);
/* 327 */       return j;
/*     */     }
/* 329 */     return this.m_notFoundValue;
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
/* 340 */     return new ObjectIntHashMap(this);
/*     */   }
/*     */ }

