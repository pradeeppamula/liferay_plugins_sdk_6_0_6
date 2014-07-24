package org.computer.portal.service.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.persistence.GroupFinder;
import com.liferay.portal.service.persistence.GroupUtil;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class GroupFinderImpl extends com.liferay.portal.service.persistence.GroupFinderImpl {

	public static String COUNT_IC_BY_C_N_D =
		GroupFinder.class.getName() + ".countICByC_N_D";

	public static String FIND_IC_BY_C_N_D =
		GroupFinder.class.getName() + ".findICByC_N_D";


	public int countICByC_N_D(
			long companyId, String name, String description,
			LinkedHashMap<String, Object> params)
		throws SystemException {
		_log.debug("***** countICByC_N_D(" + companyId + ", " + name + ", " + description + ") *****");

		name = StringUtil.lowerCase(name);
		description = StringUtil.lowerCase(description);

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		Long userId = (Long)params.get("usersGroups");
		_log.debug("userId = " + userId);

		LinkedHashMap<String, Object> params1 = params;

		LinkedHashMap<String, Object> params2 =
			new LinkedHashMap<String, Object>();

		params2.putAll(params1);

		if (userId != null) {
			params2.remove("usersGroups");
			params2.put("groupsOrgs", userId);
		}

		LinkedHashMap<String, Object> params3 =
			new LinkedHashMap<String, Object>();

		params3.putAll(params1);

		if (userId != null) {
			params3.remove("usersGroups");
			params3.put("groupsUserGroups", userId);
		}

		Session session = null;

		try {
			_log.debug("open session");
			session = openSession();

			_log.debug("get count");
			int count = countICByC_N_D(
				session, companyId, name, description, params1);

			if (Validator.isNotNull(userId)) {
				_log.debug("get more");
				count += countICByC_N_D(
					session, companyId, name, description, params2);

				_log.debug("get more");
				count += countICByC_N_D(
					session, companyId, name, description, params3);
			}

			_log.debug("***** return count = " + count + " *****");
			return count;
		}
		catch (Exception e) {
			_log.error(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
			throw new SystemException(e);
		}
		finally {
			_log.debug("close session");
			closeSession(session);
		}
	}

	protected int countICByC_N_D(
		Session session, long companyId, String name, String description,
		LinkedHashMap<String, Object> params) {

		String sql = CustomSQLUtil.get(COUNT_IC_BY_C_N_D);
		_log.debug("COUNT_IC_BY_C_N_D: " + sql);

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params));
		_log.debug("After string substitution: " + sql);

		SQLQuery q = session.createSQLQuery(sql);

		q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

		QueryPos qPos = QueryPos.getInstance(q);

		setJoin(qPos, params);
		qPos.add(companyId);
		qPos.add(name);
		qPos.add(name);
		qPos.add(description);
		qPos.add(description);

		Iterator<Long> itr = q.list().iterator();

		if (itr.hasNext()) {
			Long count = itr.next();

			if (count != null) {
				return count.intValue();
			}
		}

		return 0;
	}

	public List<Group> findICByC_N_D(
			long companyId, String name, String description,
			LinkedHashMap<String, Object> params, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		name = StringUtil.lowerCase(name);
		description = StringUtil.lowerCase(description);

		if (params == null) {
			params = new LinkedHashMap<String, Object>();
		}

		Long userId = (Long)params.get("usersGroups");

		LinkedHashMap<String, Object> params1 = params;

		LinkedHashMap<String, Object> params2 =
			new LinkedHashMap<String, Object>();

		params2.putAll(params1);

		if (userId != null) {
			params2.remove("usersGroups");
			params2.put("groupsOrgs", userId);
		}

		LinkedHashMap<String, Object> params3 =
			new LinkedHashMap<String, Object>();

		params3.putAll(params1);

		if (userId != null) {
			params3.remove("usersGroups");
			params3.put("groupsUserGroups", userId);
		}

		StringBuilder sb = new StringBuilder();

		sb.append("(");

		sb.append(CustomSQLUtil.get(FIND_IC_BY_C_N_D));
		_log.debug("findICByC_N_D = " + sb.toString());

		String sql = sb.toString();

		sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params1));
		sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params1));

		sb = new StringBuilder();

		sb.append(sql);

		sb.append(")");

		if (Validator.isNotNull(userId)) {
			sb.append(" UNION (");

			sb.append(CustomSQLUtil.get(FIND_BY_C_N_D));

			sql = sb.toString();

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params2));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params2));

			sb = new StringBuilder();

			sb.append(sql);

			sb.append(") UNION (");

			sb.append(CustomSQLUtil.get(FIND_IC_BY_C_N_D));

			sql = sb.toString();

			sql = StringUtil.replace(sql, "[$JOIN$]", getJoin(params3));
			sql = StringUtil.replace(sql, "[$WHERE$]", getWhere(params3));

			sb = new StringBuilder();

			sb.append(sql);

			sb.append(")");
		}

		sql = sb.toString();
		sql = CustomSQLUtil.replaceOrderBy(sql, obc);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("groupId", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			setJoin(qPos, params1);
			qPos.add(companyId);
			qPos.add(name);
			qPos.add(name);
			qPos.add(description);
			qPos.add(description);

			if (Validator.isNotNull(userId)) {
				setJoin(qPos, params2);
				qPos.add(companyId);
				qPos.add(name);
				qPos.add(name);
				qPos.add(description);
				qPos.add(description);

				setJoin(qPos, params3);
				qPos.add(companyId);
				qPos.add(name);
				qPos.add(name);
				qPos.add(description);
				qPos.add(description);
			}

			List<Group> groups = new ArrayList<Group>();

			Iterator<String> itr = (Iterator<String>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				long groupId = GetterUtil.getLong(itr.next());

				Group group = GroupUtil.findByPrimaryKey(groupId);

				groups.add(group);
			}

			return groups;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(GroupFinderImpl.class);

}
