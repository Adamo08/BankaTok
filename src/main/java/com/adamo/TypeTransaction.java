package com.adamo;

public enum TypeTransaction{
    VIRINI, // Transaction entre 2 clients de la meme Banque
    VIREST, // Transaction entre 2 clients du meme Pays
    VIRCHAC // Transaction entre 2 clients de deux Banques differentes de deux Pays differents
}
