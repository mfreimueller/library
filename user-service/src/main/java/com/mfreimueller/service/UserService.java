package com.mfreimueller.service;

import com.mfreimueller.client.BorrowClient;
import com.mfreimueller.domain.User;
import com.mfreimueller.dto.BorrowedBookDto;
import com.mfreimueller.dto.CreateUserDto;
import com.mfreimueller.dto.UpdateUserDto;
import com.mfreimueller.dto.UserDto;
import com.mfreimueller.exception.InvalidStateException;
import com.mfreimueller.mapper.UserMapper;
import com.mfreimueller.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

@Service
public class UserService {
    @Autowired
    private BorrowClient borrowClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public UserDto getUser(Long userId) {
        return userRepository.findById(userId)
                .filter(User::isActive)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    public List<UserDto> getAllUsers() {
        return getAllWithFilterPredicate(User::isActive);
    }

    public List<UserDto> getAllDeletedUsers() {
        return getAllWithFilterPredicate(not(User::isActive));
    }

    private List<UserDto> getAllWithFilterPredicate(Predicate<User> predicate) {
        Assert.notNull(predicate, "predicate must not be null.");

        return userRepository.findAll()
                .stream()
                .filter(predicate)
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDto createUser(CreateUserDto createUserDto) {
        Assert.notNull(createUserDto, "createUserDto must not be null.");

        val user = new User(null, createUserDto.firstName(), createUserDto.lastName(), createUserDto.email(),
                createUserDto.birthday(), null, null, true);

        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto updateUser(Long userId, UpdateUserDto updateUserDto) {
        Assert.notNull(userId, "userId must not be null.");
        Assert.notNull(updateUserDto, "updateUserDto must not be null.");

        User user = userRepository.findById(userId).filter(User::isActive).orElseThrow();
        userMapper.updateUserFromDto(updateUserDto, user);

        return userMapper.toDto(userRepository.save(user));
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
            throw new InvalidStateException(userId.toString(), "The user has already been deleted.");
        }

        val borrowedBooks = borrowClient.getBorrowedBooksByUserId(userId);
        if (borrowedBooks.stream().anyMatch(BorrowedBookDto::isBorrowed)) {
            throw new InvalidStateException(userId.toString(), "The user has borrowed at least one book.");
        }

        user.setDeletedAt(LocalDate.now());
        userRepository.save(user);
    }
}
