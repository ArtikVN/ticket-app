package cinema.dao.impl;

import cinema.dao.AbstractDao;
import cinema.dao.RoleDao;
import cinema.model.Role;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends AbstractDao<Role> implements RoleDao {
    public RoleDaoImpl(SessionFactory factory) {
        super(factory, Role.class);
    }

    @Override
    public Optional<Role> getByName(String name) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Role WHERE name = :name", Role.class)
                    .setParameter("name", Role.RoleName.valueOf(name))
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new RuntimeException("Can't find role with name - " + name, e);
        }
    }
}
