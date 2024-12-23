    package org.example.newsWebsite.service.impl;

    import org.example.newsWebsite.model.User;
    import org.example.newsWebsite.repository.UserRepository;
    import org.example.newsWebsite.service.api.UserService;
    import org.example.newsWebsite.service.api.uploading.StorageService;
    import org.example.newsWebsite.service.impl.uploading.FileSystemStorageService;
    import org.example.newsWebsite.service.impl.uploading.StorageDirectory;
    import org.example.newsWebsite.utils.StringUtils;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.io.Resource;
    import org.springframework.stereotype.Service;
    import org.springframework.web.multipart.MultipartFile;

    import java.util.List;
    import java.util.Objects;

    @Service
    public class UserServiceImpl implements UserService {
        @Autowired
        private UserRepository userRepository;

        private StorageService storageService;

        public UserServiceImpl() {
            this.storageService = new FileSystemStorageService(StorageDirectory.USER);
        }


        @Override
        public User addUser(User user) {
            userRepository.save(user);
            return user;
        }

        @Override
        public Boolean longIn(String username, String password) {
            for (User id : userRepository.findAll()) {
                if(id.getUsername().equals(username)) {
                    if(id.getPassword().equals(password)) {
                        return true;
                    }{
                        return false;
                    }
                }
            }
            return null;
        }

        @Override
        public User editUser(User user) {
            User u = userRepository.findById(user.getId()).get();
            if(Objects.nonNull(user.getUsername()) && !user.getUsername().isEmpty()) {
                u.setUsername(user.getUsername());
            }
            if(Objects.nonNull(user.getPassword()) && !user.getPassword().isEmpty()) {
                u.setPassword(user.getPassword());
            }
            if(Objects.nonNull(user.getEmail()) && !user.getEmail().isEmpty()) {
                u.setEmail(user.getEmail());
            }
            if(Objects.nonNull(user.getPhone()) && !user.getPhone().isEmpty()) {
                u.setPhone(user.getPhone());
            }
            if(Objects.nonNull(user.getFavorites())){
                u.setFavorites(user.getFavorites());
            }

            return userRepository.save(u);
        }

        @Override
        public boolean deleteUser(String username) {
            if(isUserExist(username)) {
                userRepository.delete(this.getUser(username));
                return true;
            }
            return false;
        }

        @Override
        public boolean deleteUser(Long id) {
            if(isUserExist(id)) {
                userRepository.delete(this.getUser(id));
                return true;
            }
            return false;
        }

        @Override
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        @Override
        public User getUser(String username) {
            for(User user : userRepository.findAll()) {
                if(user.getUsername().equals(username))
                    return user;
            }
            return null;
        }

        @Override
        public User getUser(Long id) {
            if(userRepository.existsById(id)){
                return userRepository.findById(id).get();
            }
            return null;
        }

        @Override
        public Boolean uploadProfileImage(MultipartFile file, Long id) {
            if(this.isUserExist(id)) {

                String fileName = id + "." + StringUtils.extractPostfix(file.getOriginalFilename());
                boolean status =  storageService.store(file, fileName);

                if(status){

                    User user = this.getUser(id);
                    user.setPhotoPath(fileName);
                    userRepository.save(user);
                    return true;
                }
            }
            return false;
        }

        @Override
        public Resource downloadProfileImage(Long id) {
            User user = this.getUser(id);

            if(user != null){

                String filename = user.getPhotoPath();
                if(filename != null){
                    try {
                        return storageService.loadAsResource(filename);
                    } catch (Exception e) {
                        return null;
                    }
                }
                return null;
            }
            return null;
        }

        @Override
        public boolean isUserExist(String username) {
            for(User user : userRepository.findAll()) {
                if(user.getUsername().equals(username)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean isUserExist(Long id) {
            return userRepository.existsById(id);
        }

        @Override
        public boolean isEmailExist(String email) {
            for(User user : userRepository.findAll()) {
                if(user.getEmail().equals(email)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean isPhoneExist(String phone) {
            for(User user : userRepository.findAll()) {
                if(user.getPhone().equals(phone)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public Integer getNumberOfUsers() {
            return userRepository.findAll().size();
        }

        @Override
        public Boolean changeFavorites(Long id, Byte favorites) {
            if(this.isUserExist(id)){
                User user = userRepository.findById(id).get();
                user.setFavorites(favorites);
                userRepository.save(user);
                return true;
            }
            return false;
        }

        @Override
        public Byte getFavorites(Long id) {
            if(this.isUserExist(id)){
                return userRepository.findById(id).get().getFavorites();
            }
            return null;
        }
    }
