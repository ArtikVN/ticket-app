package ticket.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ticket.dao.TicketDao;
import ticket.exception.DataProcessingException;
import ticket.lib.Dao;
import ticket.model.Ticket;
import ticket.util.HibernateUtil;

@Dao
public class TicketDaoImpl implements TicketDao {

    @Override
    public Ticket add(Ticket ticket) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't create ticket: " + ticket, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
