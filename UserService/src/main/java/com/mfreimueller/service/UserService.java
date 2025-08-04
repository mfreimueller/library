package com.mfreimueller.service;

import com.mfreimueller.client.BorrowClient;
import com.mfreimueller.domain.User;
import com.mfreimueller.dto.CreateUserDto;
import com.mfreimueller.dto.UserDto;
import com.mfreimueller.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private BorrowClient borrowClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConversionService conversionService;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> conversionService.convert(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        Assert.notNull(createUserDto, "createUserDto must not be null.");

        val user = new User(null, createUserDto.firstName(), createUserDto.lastName(), createUserDto.email(),
                createUserDto.username(), createUserDto.password(), createUserDto.birthday(), null);

        return conversionService.convert(userRepository.save(user), UserDto.class);
    }

    /// Attempts to delete a user. This method fails in any of these cases:
    /// - no user is connected to the given userId, or
    /// - the user identified by the given userId has already been deleted, or
    /// - there are still books that the user has to return.
    /// In any case this method only soft-deletes the user by setting the
    /// current date as the deletion-date.
    public void deleteUser(Long userId) {
        Assert.notNull(userId, "userId must not be null.");

        var user = userRepository.findById(userId).orElseThrow();
        if (user.getDeletedAt() != null) {
            // TODO: log & throw
            return;
        }

        val borrowedBooks = borrowClient.getBorrowedBooksByUserId(userId);
        if (!borrowedBooks.isEmpty()) {
            // TODO: log & throw
            return;
        }

        user.setDeletedAt(LocalDate.now());
        userRepository.save(user);
    }
}
