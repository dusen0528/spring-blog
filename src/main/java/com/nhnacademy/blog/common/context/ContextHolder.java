/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.blog.common.context;

// TODO#1-9 Context에 접근할 수 있도록 ContextHolder를 싱글톤으로 구현합니다.
public class ContextHolder {

    //ApplicationContext를 초기화 합니다.
    private static final Context context = null;

    private ContextHolder(){
        //ContextHoder를 new ContextHolder() 시도 한다면 IllegalStateException 예외가 발생할 수 있도록 구현 합니다.

    }

    public static synchronized ApplicationContext getApplicationContext() {
        //ApplicationContext 반환 합니다.
        return null;
    }
}