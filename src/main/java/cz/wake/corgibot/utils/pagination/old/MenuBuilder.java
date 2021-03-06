package cz.wake.corgibot.utils.pagination.old;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.jagrosh.jdautilities.menu.Menu;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled for removal in 2.0, replacement will be inner class of corresponding menu.
 * This is effective for <b>ALL MENUBUILDERS</b>. The corresponding location of these
 * classes will also be flattened down to {@link com.jagrosh.jdautilities.menu}.
 *
 * <p><b>PREPARE ACCORDINGLY!</b> If you have implemented your own Menus and MenuBuilders
 * please be ready to make adjustments to them.
 *
 * <p>Full information on these and other 2.0 deprecations and changes can be found
 * <a href="https://gist.github.com/TheMonitorLizard/4f09ac2a3c9d8019dc3cde02cc456eee">here</a>
 *
 * @author John Grosh
 */
public abstract class MenuBuilder<T extends MenuBuilder<T, V>, V extends Menu> {
    protected final Set<User> users = new HashSet<>();
    protected final Set<Role> roles = new HashSet<>();
    protected EventWaiter waiter;
    protected long timeout = 1;
    protected TimeUnit unit = TimeUnit.MINUTES;

    /**
     * Builds the {@link com.jagrosh.jdautilities.menu.Menu Menu} corresponding to
     * this {@link MenuBuilder}.
     * <br>After doing this, no modifications of the displayed Menu can be made.
     *
     * @return The built Menu of corresponding type to this MenuBuilder.
     */
    public abstract V build();

    /**
     * Sets the {@link java.awt.Color Color} of thu,
     * if description of the MessageEmbed is set.
     *
     * @param color The Color of the MessageEmbed
     * @return This builder
     * @deprecated This will be removed in 2.0 due to the bias it posses towards non-embed menus.<br>
     * If you wish to continue using it for your own custom Menu implementations in 2.0,
     * you simply have to remove the @Override annotation.
     */
    @Deprecated
    public abstract T setColor(Color color);

    /**
     * Sets the {@link EventWaiter}
     * that will do {@link com.jagrosh.jdautilities.menu.Menu Menu} operations.
     *
     * <p><b>NOTE:</b> All Menus will only work with an EventWaiter set!
     * <br>Not setting an EventWaiter means the Menu will not work.
     *
     * @param waiter The EventWaiter
     * @return This builder
     */
    public final T setEventWaiter(EventWaiter waiter) {
        this.waiter = waiter;
        return (T) this;
    }

    /**
     * Adds {@link User}s that are allowed to use the
     * {@link com.jagrosh.jdautilities.menu.Menu Menu} that will be built.
     *
     * @param users The Users allowed to use the Menu
     * @return This builder
     */
    public final T addUsers(User... users) {
        this.users.addAll(Arrays.asList(users));
        return (T) this;
    }

    /**
     * Sets {@link User}s that are allowed to use the
     * {@link com.jagrosh.jdautilities.menu.Menu Menu} that will be built.
     * <br>This clears any Users already registered before adding the ones specified.
     *
     * @param users The Users allowed to use the Menu
     * @return This builder
     */
    public final T setUsers(User... users) {
        this.users.clear();
        this.users.addAll(Arrays.asList(users));
        return (T) this;
    }

    /**
     * Adds {@link Role}s that are allowed to use the
     * {@link com.jagrosh.jdautilities.menu.Menu Menu} that will be built.
     *
     * @param roles The Roles allowed to use the Menu
     * @return This builder
     */
    public final T addRoles(Role... roles) {
        this.roles.addAll(Arrays.asList(roles));
        return (T) this;
    }

    /**
     * Sets {@link Role}s that are allowed to use the
     * {@link com.jagrosh.jdautilities.menu.Menu Menu} that will be built.
     * <br>This clears any Roles already registered before adding the ones specified.
     *
     * @param roles The Roles allowed to use the Menu
     * @return This builder
     */
    public final T setRoles(Role... roles) {
        this.roles.clear();
        this.roles.addAll(Arrays.asList(roles));
        return (T) this;
    }

    /**
     * Sets the timeout that the {@link com.jagrosh.jdautilities.menu.Menu Menu} should
     * stay available.
     *
     * <p>After this has expired, the a final action in the form of a
     * {@link java.lang.Runnable} may execute.
     *
     * @param timeout The amount of time for the Menu to stay available
     * @param unit    The {@link java.util.concurrent.TimeUnit TimeUnit} for the timeout
     * @return This builder
     */
    public final T setTimeout(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
        return (T) this;
    }
}
