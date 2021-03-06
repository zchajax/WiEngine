/*
 * Copyright (c) 2010 WiYun Inc.
 * Author: luma(stubma@gmail.com)
 *
 * For all entities this program is free software; you can redistribute
 * it and/or modify it under the terms of the 'WiEngine' license with
 * the additional provision that 'WiEngine' must be credited in a manner
 * that can be be observed by end users, for example, in the credits or during
 * start up. (please find WiEngine logo in sdk's logo folder)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.wiyun.engine.grid;

import com.wiyun.engine.BaseWYObject;
import com.wiyun.engine.nodes.Node;
import com.wiyun.engine.types.WYPoint;

/**
 * 网格的基类。
 */
public class BaseGrid extends BaseWYObject {
	public static BaseGrid from(int pointer) {
		return pointer == 0 ? null : new BaseGrid(pointer);
	}
	
	protected BaseGrid() {
	}
	
	protected BaseGrid(int pointer) {
		super(pointer);
	}

	/**
     * 得到单个网格的长宽尺寸
     * 
     * @return {@link WYPoint}
     */
    public WYPoint getStep() {
    	return WYPoint.make(getStepWidth(), getStepHeight());
    }

    /**
     * 如果网格功能被启用，则一帧渲染完后会调用此方法
     * 
     * @param target 网格的目标节点
     */
    public void afterDraw(Node target) {
    	nativeAfterDraw(target.getPointer());
    }
    
    private native void nativeAfterDraw(int pointer);
    
    /**
     * 在一帧绘制之前被调用
     */
    public native void beforeDraw();
    
    /**
     * 得到网格的总高度
     * 
     * @return 网格总象素高度
     */
    public native int getGridHeight();

    /**
     * 得到网格总宽度
     * 
     * @return 网格总象素宽度
     */
    public native int getGridWidth();
    
    /**
     * 得到单格的宽度
     * 
     * @return 单格的宽度
     */
    public native int getStepWidth();
    
    /**
     * 得到单格的高度
     * 
     * @return 单格的高度
     */
    public native int getStepHeight();

    /**
     * 得到网格是否激活的标志, 网格如果不在激活状态，不会影响节点的渲染。
     * 
     * @return true表示网格当前是激活的
     */
    public native boolean isActive();
    
    /**
     * 增加重用次数
     * 
     * @param add 要增加的重用次数
     */
    public native void addReuseCount(int add);
    
    /**
     * 子类需要实现这个方法来完成网格效果的渲染
     */
    public native void blit();
    
    /**
     * 重用网格，子类需要实现这个方法来完成重用逻辑。一般来说就是恢复到初始状态。
     */
    public native void reuse();
    
    /**
     * 检查是否应该重用网格
     * 
     * @return 如果网格的重用计数还不为0，则返回true
     */
    public native boolean isReuseGrid();

    /**
     * 设置网格激活标志
     * 
     * @param flag true表示激活，false表示不激活
     */
    public native void setActive(boolean flag);
}
