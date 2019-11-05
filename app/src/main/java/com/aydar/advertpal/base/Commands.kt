package com.aydar.advertpal.base

sealed class Commands {

    class ShowProgress : Commands()

    class HideProgress : Commands()

    class HasWorks : Commands()

    class HasNoWorks : Commands()

    class DeletingWorkInProgress : Commands()

    class WorkDeleted : Commands()

    class ErrorWhileDelete : Commands()
}