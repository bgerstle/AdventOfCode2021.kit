val Int.seriesSum: Int
    get() {
        assert(this >= 0)
        return when {
            this <= 1 -> this
            else -> (1..this).sum()
        }
    }
