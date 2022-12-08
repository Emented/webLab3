package com.emented.weblab3.service;

import com.emented.weblab3.model.Hit;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class HitCheckerImpl implements HitChecker {

    @Override
    public boolean checkHit(Hit hit) {
        return checkInFirstQuoterTriangle(hit)
                || checkInThirdQuoterSquare(hit)
                || checkInFourthQuoterCircle(hit);
    }

    private boolean checkInFirstQuoterTriangle(Hit hit) {
        return hit.getX() >= 0
                && hit.getY() >= 0
                && hit.getY() <= hit.getR() - hit.getX();
    }

    private boolean checkInThirdQuoterSquare(Hit hit) {
        return hit.getX() <= 0
                && hit.getY() <= 0
                && hit.getX() >= -hit.getR()
                && hit.getY() >= -hit.getR();
    }

    private boolean checkInFourthQuoterCircle(Hit hit) {
        return hit.getX() >= 0
                && hit.getY() <= 0
                && Math.pow(hit.getX(), 2) + Math.pow(hit.getY(), 2) <= Math.pow(hit.getR(), 2);
    }
}
