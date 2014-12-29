/**
 *
 */
package com.teefun.db.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.teefun.db.dao.QueueDAO;
import com.teefun.db.entity.QueueEntity;
import com.teefun.db.service.QueueService;
import com.teefun.model.Queue;

/**
 * Default impl for {@link QueueService}.
 *
 * @author Rajh
 *
 */
@Service
public class QueueServiceImpl implements QueueService {

	/**
	 * Class logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueServiceImpl.class);

	/**
	 * The queue dao.
	 */
	@Resource
	private QueueDAO queueDao;

	@Override
	public List<Queue> getQueues() {
		final List<Queue> queues = new ArrayList<Queue>();
		final List<QueueEntity> queuesEntities = this.queueDao.list();
		for (final QueueEntity queueEntity : queuesEntities) {
			if (queueEntity.isActive()) {
				// FIXME mapping lib ?
				queues.add(new Queue(queueEntity.getName(), queueEntity.getMaxSize(), queueEntity.getMap(), queueEntity.getGametype(), queueEntity.getScoreLimit(), queueEntity.getTimeLimit(), true));
			}
		}
		return queues;
	}

	@Override
	@Transactional
	public void saveQueues(final List<Queue> queues) {
		for (final Queue queue : queues) {
			if (queue.isPermanent()) {
				// FIXME mapping lib ?
				final QueueEntity queueEntity = new QueueEntity();
				queueEntity.setActive(true);
				queueEntity.setGametype(queue.getGametype());
				queueEntity.setMap(queue.getMap());
				queueEntity.setMaxSize(queue.getMaxSize());
				queueEntity.setName(queue.getName());
				queueEntity.setScoreLimit(queue.getScoreLimit());
				queueEntity.setTimeLimit(queue.getTimeLimit());
				this.queueDao.save(queueEntity);
				LOGGER.trace("Queue {} saved", new Object[] { queueEntity });
			}
		}
	}

	@Override
	@Transactional
	public void saveQueue(final Queue queue) {
		if (queue.isPermanent()) {
			// FIXME mapping lib ?
			final QueueEntity queueEntity = new QueueEntity();
			queueEntity.setActive(true);
			queueEntity.setGametype(queue.getGametype());
			queueEntity.setMap(queue.getMap());
			queueEntity.setMaxSize(queue.getMaxSize());
			queueEntity.setName(queue.getName());
			queueEntity.setScoreLimit(queue.getScoreLimit());
			queueEntity.setTimeLimit(queue.getTimeLimit());
			this.queueDao.save(queueEntity);
			LOGGER.trace("Queue {} saved", new Object[] { queueEntity });
		}
	}

	@Override
	@Transactional
	public void removeQueue(final Queue queue) {
		if (queue.isPermanent()) {
			final QueueEntity queueEntity = this.queueDao.getByName(queue.getName());
			if (queueEntity != null) {
				this.queueDao.remove(queueEntity);
				LOGGER.trace("Queue {} removed", new Object[] { queueEntity });
			}
		}
	}

}
