package com.nhnacademy.blog.common.init;

import com.nhnacademy.blog.common.context.Context;

/** TODO#1-1 [README] Initializeable interface
 * - application이 시작될 때 initialize()를 호출해서 초기화 합니다.
 * - Application Context에 의해서 initialize() 메서드가 호출 됩니다.
 * - Application 시작시점에 초기화가 필요할 때 Initializeable을 구현 합니다.
 */
public interface Initializeable {
    void initialize(Context context);
}
