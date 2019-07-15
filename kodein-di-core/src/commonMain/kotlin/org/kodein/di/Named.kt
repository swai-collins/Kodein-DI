package org.kodein.di

@Suppress("EXPERIMENTAL_FEATURE_WARNING")
inline class Named(val kodein: KodeinAware) {
    /**
     * Gets a factory of [T] for the given argument type and return type.
     * The name of the receiving property is used as tag.
     *
     * @param A The type of argument the returned factory takes.
     * @param T The type of object to retrieve with the returned factory.
     * @param argType The type of argument the returned factory takes.
     * @param type The type of object to retrieve with the returned factory.
     * @return A factory of [T].
     * @throws Kodein.NotFoundException If no factory was found.
     * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
     */
    fun <A, T : Any> Factory(argType: TypeToken<in A>, type: TypeToken<out T>): KodeinProperty<(A) -> T> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.factory(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value) }

    /**
     * Gets a factory of [T] for the given argument type and return type, or null if none is found.
     * The name of the receiving property is used as tag.
     *
     * @param A The type of argument the returned factory takes.
     * @param T The type of object to retrieve with the returned factory.
     * @param argType The type of argument the returned factory takes.
     * @param type The type of object to retrieve with the returned factory.
     * @return A factory of [T], or null if no factory was found.
     * @throws Kodein.DependencyLoopException When calling the factory, if the value construction triggered a dependency loop.
     */
    fun <A, T : Any> FactoryOrNull(argType: TypeToken<in A>, type: TypeToken<out T>): KodeinProperty<((A) -> T)?> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.factoryOrNull(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value) }

    /**
     * Gets a provider of [T] for the given type.
     * The name of the receiving property is used as tag.
     *
     * @param T The type of object to retrieve with the returned provider.
     * @param type The type of object to retrieve with the returned provider.
     * @return A provider of [T].
     * @throws Kodein.NotFoundException If no provider was found.
     * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
     */
    fun <T : Any> Provider(type: TypeToken<out T>): KodeinProperty<() -> T> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.provider(Kodein.Key(ctx.anyType, UnitToken, type, tag), ctx.value) }

    /**
     * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A].
     * The name of the receiving property is used as tag.
     *
     * @param A The type of argument the curried factory takes.
     * @param T The type of object to retrieve with the returned provider.
     * @param argType The type of argument the curried factory takes.
     * @param type The type of object to retrieve with the returned provider.
     * @param arg A function that returns the argument that will be given to the factory when curried.
     * @return A provider of [T].
     * @throws Kodein.NotFoundException If no provider was found.
     * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
     */
    fun <A, T : Any> Provider(argType: TypeToken<in A>, type: TypeToken<out T>, arg: () -> A): KodeinProperty<() -> T> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.factory(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value).toProvider(arg) }

    /**
     * Gets a provider of [T] for the given type, or null if none is found.
     * The name of the receiving property is used as tag.
     *
     * @param T The type of object to retrieve with the returned provider.
     * @param type The type of object to retrieve with the returned provider.
     * @return A provider of [T], or null if no provider was found.
     * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
     */
    fun <T : Any> ProviderOrNull(type: TypeToken<out T>): KodeinProperty<(() -> T)?> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.providerOrNull(Kodein.Key(ctx.anyType, UnitToken, type, tag), ctx.value) }

    /**
     * Gets a provider of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
     * The name of the receiving property is used as tag.
     *
     * @param A The type of argument the curried factory takes.
     * @param T The type of object to retrieve with the returned provider.
     * @param argType The type of argument the curried factory takes.
     * @param type The type of object to retrieve with the returned provider.
     * @param arg A function that returns the argument that will be given to the factory when curried.
     * @return A provider of [T], or null if no factory was found.
     * @throws Kodein.DependencyLoopException When calling the provider, if the value construction triggered a dependency loop.
     */
    fun <A, T : Any> ProviderOrNull(argType: TypeToken<in A>, type: TypeToken<out T>, arg: () -> A): KodeinProperty<(() -> T)?> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.factoryOrNull(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value)?.toProvider(arg) }

    /**
     * Gets an instance of [T] for the given type.
     * The name of the receiving property is used as tag.
     *
     * @param T The type of object to retrieve.
     * @param type The type of object to retrieve.
     * @return An instance of [T].
     * @throws Kodein.NotFoundException If no provider was found.
     * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
     */
    fun <T : Any> Instance(type: TypeToken<out T>): KodeinProperty<T> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.provider(Kodein.Key(ctx.anyType, UnitToken, type, tag), ctx.value).invoke() }

    /**
     * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A].
     * The name of the receiving property is used as tag.
     *
     * @param A The type of argument the curried factory takes.
     * @param T The type of object to retrieve.
     * @param argType The type of argument the curried factory takes.
     * @param type The type of object to retrieve.
     * @param arg A function that returns the argument that will be given to the factory when curried.
     * @return An instance of [T].
     * @throws Kodein.NotFoundException If no provider was found.
     * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
     */
    fun <A, T : Any> Instance(argType: TypeToken<in A>, type: TypeToken<T>, arg: () -> A): KodeinProperty<T> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.factory(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value).invoke(arg()) }

    /**
     * Gets an instance of [T] for the given type, or null if none is found.
     * The name of the receiving property is used as tag.
     *
     * @param type The type of object to retrieve.
     * @return An instance of [T], or null if no provider was found.
     * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
     */
    fun <T : Any> InstanceOrNull(type: TypeToken<out T>): KodeinProperty<T?> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.providerOrNull(Kodein.Key(ctx.anyType, UnitToken, type, tag), ctx.value)?.invoke() }

    /**
     * Gets an instance of [T] for the given type, curried from a factory that takes an argument [A], or null if none is found.
     * The name of the receiving property is used as tag.
     *
     * @param A The type of argument the curried factory takes.
     * @param T The type of object to retrieve.
     * @param type The type of object to retrieve.
     * @param arg A function that returns the argument that will be given to the factory when curried.
     * @return An instance of [T], or null if no factory was found.
     * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
     */
    fun <A, T : Any> InstanceOrNull(argType: TypeToken<in A>, type: TypeToken<out T>, arg: () -> A): KodeinProperty<T?> =
            KodeinProperty(kodein.kodeinTrigger, kodein.kodeinContext) { ctx, tag -> kodein.kodein.container.factoryOrNull(Kodein.Key(ctx.anyType, argType, type, tag), ctx.value)?.invoke(arg()) }

}

/**
 * Allows to get factories / providers / instances with a tag set to the name of the receiving property.
 */
val KodeinAware.named: Named get() = Named(this)

/**
 * Gets a constant of type [T] and tag whose tag is the name of the receiving property.
 *
 * @param T The type of object to retrieve.
 * @param type The type of object to retrieve.
 * @return An instance of [T].
 * @throws Kodein.NotFoundException If no provider was found.
 * @throws Kodein.DependencyLoopException If the value construction triggered a dependency loop.
 */
fun <T : Any> KodeinAware.Constant(type: TypeToken<out T>) = named.Instance(type)