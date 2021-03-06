package fluent.api.model.impl;

import fluent.api.model.ModifiersModel;
import fluent.api.model.TypeModel;
import fluent.api.model.VarModel;

public class VarModelImpl extends ElementModelImpl implements VarModel {

    private final TypeModel type;
    private final String name;
    private String initializer;

    public VarModelImpl(ModifiersModel modifiers, TypeModel type, String name) {
        super(modifiers);
        this.type = type;
        this.name = name;
    }

    @Override
    public TypeModel type() {
        return type;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String initializer() {
        return initializer;
    }

    @Override
    public VarModel initializer(String initializer) {
        this.initializer = initializer;
        return this;
    }

    @Override
    public String toString() {
        return type() + " " + name();
    }
}
