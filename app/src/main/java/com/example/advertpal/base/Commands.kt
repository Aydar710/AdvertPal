package com.example.advertpal.base

sealed class Commands {

    class ShowProgress : Commands()

    class HideProgress : Commands()

    class HasWorks() : Commands()

    class HasNoWorks() : Commands()
}