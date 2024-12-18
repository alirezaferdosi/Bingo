    package org.example.newsWebsite.model.convertor;

    import org.example.newsWebsite.model.View;
    import org.example.newsWebsite.model.dto.ViewDto;
    import org.example.newsWebsite.service.api.NewsService;
    import org.example.newsWebsite.service.api.UserService;
    import org.springframework.beans.factory.annotation.Qualifier;
    import org.springframework.stereotype.Component;

    @Component("ViewConvertor")
    public class ViewConvertor implements PrimitiveConvertor<View, ViewDto>{
        @Qualifier("userService")
        private UserService userService;

        @Qualifier("newsService")
        private NewsService newsService;

        @Override
        public ViewDto modelToDto(View model) {
            return new ViewDto(
                    model.getId(),
                    model.getUser().getId(),
                    model.getNews().getId()
            );
        }

        @Override
        public View dtoToModel(ViewDto dto) {
            return new View(
                    dto.getId(),
                    userService.getUser(dto.getUser()),
                    newsService.getNews(dto.getNews())
            );
        }
    }
