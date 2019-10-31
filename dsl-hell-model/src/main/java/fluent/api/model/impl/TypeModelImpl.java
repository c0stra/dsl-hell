package fluent.api.model.impl;

import fluent.api.model.MethodModel;
import fluent.api.model.ModifiersModel;
import fluent.api.model.TypeModel;
import fluent.api.model.VarModel;

import javax.lang.model.type.TypeKind;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class TypeModelImpl extends GenericModelImpl<TypeModel> implements TypeModel {
    private final String packageName;
    private final String simpleName;
    private final String fullName;
    private final TypeKind kind;
    private TypeModel rawType = this;
    private TypeModel componentType = this;
    private TypeModel superClass;
    private List<VarModel> fields = new ArrayList<>();
    private List<MethodModel> methods = new ArrayList<>();
    private List<TypeModel> interfaces = new ArrayList<>();
    public TypeModelImpl(ModifiersModel modifiers, String packageName, String simpleName, String fullName, TypeKind kind) {
        super(modifiers);
        this.packageName = packageName;
        this.simpleName = simpleName;
        this.fullName = fullName;
        this.kind = kind;
    }

    public TypeModelImpl(ModifiersModel modifiers, String packageName, String simpleName, String fullName, TypeKind kind, List<TypeModel> typeParameters) {
        this(modifiers, packageName, simpleName, fullName, kind);
        this.typeParameters().addAll(typeParameters);
    }

    @Override
    public String simpleName() {
        return simpleName;
    }

    @Override
    public String packageName() {
        return packageName;
    }

    @Override
    public String fullName() {
        return fullName;
    }

    @Override
    public boolean isArray() {
        return kind == TypeKind.ARRAY;
    }

    @Override
    public boolean isTypeVariable() {
        return kind == TypeKind.TYPEVAR;
    }

    @Override
    public TypeModel rawType() {
        return rawType;
    }

    @Override
    public TypeModel rawType(TypeModel rawType) {
        this.rawType = rawType;
        return this;
    }

    @Override
    public TypeModel componentType() {
        return componentType;
    }

    @Override
    public TypeModel componentType(TypeModel componentType) {
        this.componentType = componentType;
        return this;
    }

    @Override
    public List<MethodModel> methods() {
        return methods;
    }

    @Override
    public TypeModel methods(List<MethodModel> methods) {
        this.methods = methods;
        return this;
    }

    @Override
    public List<VarModel> fields() {
        return fields;
    }

    @Override
    public TypeModel fields(List<VarModel> fields) {
        this.fields = fields;
        return this;
    }

    @Override
    public TypeModel superClass() {
        return superClass;
    }

    @Override
    public List<TypeModel> interfaces() {
        return interfaces;
    }

    @Override
    public TypeModel superClass(TypeModel superClass) {
        this.superClass = superClass;
        return this;
    }

    @Override
    public TypeModel typeParameters(List<TypeModel> typeParameters) {
        if(typeParameters.isEmpty())
            return this;
        String collect = typeParameters.stream().map(TypeModel::fullName).collect(joining(", ", "<", ">"));
        return new TypeModelImpl(modifiers(), packageName, simpleName + collect, fullName + collect, kind, typeParameters).rawType(this);
    }

}