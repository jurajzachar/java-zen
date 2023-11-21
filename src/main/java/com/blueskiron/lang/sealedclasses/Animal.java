package com.blueskiron.lang.sealedclasses;

public sealed interface Animal permits Cheetah, Domesticated, Extinct {

  Animal parent();
}
