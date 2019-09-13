package com.example.advertpal.utils

class WorkDataKeyGenerator {

    companion object {

        fun generateTextKeyForGroup(groupId: String) =
            POST_TEXT_KEY + groupId

    }
}