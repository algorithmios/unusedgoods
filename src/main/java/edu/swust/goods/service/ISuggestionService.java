package edu.swust.goods.service;

import java.util.List;

import edu.swust.goods.domain.Suggestion;
import edu.swust.goods.view.SuggestionView;

public interface ISuggestionService extends IBaseService<Suggestion>{
    long unreadCount();
    List<SuggestionView> unreadList(Integer start);
    List<SuggestionView> allList(Integer start);
}
