package com.nexos.test.repository.criteria;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.nexos.test.entity.Commodity;
import com.nexos.test.repository.CommodityRepository;

@Component("commodityRepositoryCriteria")
public class CommodityRepositoryCriteria {

	@Autowired
	@Qualifier("commodityRepository")
	private CommodityRepository commodityRepository;

	private EntityManager em;

	public CommodityRepositoryCriteria(EntityManager em) {
		this.em = em;
	}

	public List<Commodity> getListCommodity(String name, String date, Long creatorUser) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Commodity> cq = cb.createQuery(Commodity.class);

		Root<Commodity> rootCommodity = cq.from(Commodity.class);

		cq.select(rootCommodity);

		List<Predicate> predicates = new ArrayList<>();

		if (!name.isEmpty()) {
			predicates.add(cb.like(cb.lower(rootCommodity.get("name")), "%" + name.toLowerCase() + "%"));
		}

		if (!date.isEmpty()) {

			Expression<String> dateExpr = cb.function("TO_CHAR", String.class, rootCommodity.get("dateOfAdmission"),
					cb.literal("yyyy-MM-dd"));
			predicates.add(cb.equal(dateExpr, date));
		}

		if (creatorUser != null) {
			predicates.add(cb.equal(rootCommodity.get("creatorUser"), creatorUser));
		}

		cq.where(cb.and(predicates.toArray(new Predicate[] {}))).orderBy(cb.asc(rootCommodity.get("name")));

		TypedQuery<Commodity> typedQuery = em.createQuery(cq);
		return typedQuery.getResultList();
	}
	/*
	 * private Date stringConvertToDate(String date) {
	 * 
	 * SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); Date d = null;
	 * 
	 * try { d = sdf.parse(date); } catch (ParseException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return d; }
	 */
}
