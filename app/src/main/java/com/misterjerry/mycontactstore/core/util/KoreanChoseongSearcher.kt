package com.misterjerry.mycontactstore.core.util

object KoreanChoseongSearcher {
    private const val HANGUL_BASE = 0xAC00
    private const val HANGUL_END = 0xD7A3
    private const val CHOSEONG_BASE = 588

    private val CHOSEONG_LIST = charArrayOf(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )

    fun match(target: String, query: String): Boolean {
        if (query.isEmpty()) return true
        
        // Remove spaces for looser matching? Or strict? Let's keep it simple first.
        // We will try to match the query sequence within the target string.
        // Supports:
        // 1. Standard contains (e.g., "Hong" in "HongGilDong")
        // 2. Choseong matching (e.g., "ㅎㄱㄷ" for "홍길동")
        
        var t = 0
        var q = 0
        
        while (t < target.length) {
            if (isMatch(target[t], query[q])) {
                // If first char matches, check the rest of the query
                var tempT = t + 1
                var tempQ = q + 1
                var fullMatch = true
                
                while (tempQ < query.length) {
                    if (tempT >= target.length || !isMatch(target[tempT], query[tempQ])) {
                        fullMatch = false
                        break
                    }
                    tempT++
                    tempQ++
                }
                
                if (fullMatch) return true
            }
            t++
        }
        
        return false
    }

    private fun isMatch(targetChar: Char, queryChar: Char): Boolean {
        if (queryChar == targetChar) return true
        
        // Check if queryChar is a Choseong and targetChar is a Hangul syllable
        if (isHangul(targetChar) && isChoseong(queryChar)) {
            val choseong = getChoseong(targetChar)
            return choseong == queryChar
        }
        
        return false
    }

    private fun isHangul(c: Char): Boolean {
        return c.code in HANGUL_BASE..HANGUL_END
    }

    private fun isChoseong(c: Char): Boolean {
        return c in CHOSEONG_LIST
    }

    private fun getChoseong(c: Char): Char {
        val index = (c.code - HANGUL_BASE) / CHOSEONG_BASE
        return CHOSEONG_LIST[index]
    }
}