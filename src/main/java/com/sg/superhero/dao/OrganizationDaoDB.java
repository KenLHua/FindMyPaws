package com.sg.superhero.dao;


import com.sg.superhero.model.Hero;
import com.sg.superhero.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizationDaoDB implements OrganizationDao{

    @Autowired
    JdbcTemplate jdbc;
    @Override
    public Organization getOrganizationById(int id) {
        try {
            final String SELECT_ORGANIZATION_BY_ID = "SELECT * FROM organization WHERE id = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION_BY_ID, new OrganizationDaoDB.OrganizationMapper(), id);
            organization.setHeroes(this.getHeroesForOrg(organization.getId()));

            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganization() {
        final String SELECT_ALL_ORGANIZATION = "SELECT * FROM organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATION, new OrganizationDaoDB.OrganizationMapper());
        associateHeroes(organizations);
        return organizations;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORG = "INSERT INTO organization(name,id,description,contactinfo)"
                + "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORG,
                organization.getName(),
                organization.getId(),
                organization.getDescription(),
                organization.getContactInfo());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setId(newId);
        insertOrganizationHero(organization);
        return organization;
    }
    private void insertOrganizationHero(Organization organization){

        final String INSERT_HERO_ORG = "INSERT INTO hero_organization(organizationId, heroId)"
                + "VALUES(?,?)";

        for (Hero hero : organization.getHeroes()) {
            jdbc.update(INSERT_HERO_ORG,
                    organization.getId(),
                    hero.getId());
        }

    }

    @Override
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORG = "UPDATE organization SET Name = ? , Description = ? , Contactinfo = ?"
                + "WHERE id = ?";
        jdbc.update(UPDATE_ORG,
                organization.getName(),
                organization.getDescription(),
                organization.getContactInfo(),
                organization.getId());
        final String DELETE_ORGANIZATION_HERO = "DELETE FROM hero_organization WHERE organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION_HERO, organization.getId());
        insertOrganizationHero(organization);
    }

    @Override
    public void deleteOrganizationById(int id) {
        final String DELETE_HERO_ORG = "DELETE FROM hero_organization WHERE organizationId = ?";
        jdbc.update(DELETE_HERO_ORG, id);

        final String DELETE_ORG = "DELETE FROM organization WHERE id = ?";
        jdbc.update(DELETE_ORG, id);
    }

    @Override
    public List<Organization> getOrgForHeroes(Hero hero) {
        final String SELECT_ORG_FOR_HERO = "SELECT o.* FROM organization o JOIN "
                + "hero_organization ho ON ho.organizationId = o.Id WHERE ho.heroId = ?";
        List<Organization> organizations = jdbc.query(SELECT_ORG_FOR_HERO,
                new OrganizationDaoDB.OrganizationMapper(), hero.getId());
        associateHeroes(organizations);
        return organizations;
    }

    private void associateHeroes(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setHeroes(getHeroesForOrg(organization.getId()));
        }
    }

    private List<Hero> getHeroesForOrg(int id) {
        final String SELECT_HERO_FOR_ORG = "SELECT h.* from hero h "
                + "JOIN hero_organization ho on ho.heroId = h.id WHERE organizationId = ?";
        List<Hero> heroes = jdbc.query(SELECT_HERO_FOR_ORG, new HeroDaoDB.HeroMapper(), id);
        return heroes;
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setId(rs.getInt("id"));
            organization.setName(rs.getString("Name"));
            organization.setDescription(rs.getString("Description"));
            organization.setContactInfo(rs.getString("ContactInfo"));

            return organization;
        }
    }
}
